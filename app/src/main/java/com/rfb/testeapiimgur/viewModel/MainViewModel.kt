package com.rfb.testeapiimgur.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.rfb.testeapiimgur.model.Image
import com.rfb.testeapiimgur.model.Imagens
import com.rfb.testeapiimgur.repository.ApiRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response

class MainViewModel(private val repository: ApiRepository) : ViewModel() {

    val erro = MutableLiveData<String>()
    val images = MutableLiveData<MutableList<Image>>()

    suspend fun getPhotos(q: String) {
        var response: Response<Imagens>? = null
        try {
            response = repository.getPhotos(q)
        } catch (e: Exception) {
            erro.postValue("Erro ao fazer a requisição")
        }
        if (response != null && response.isSuccessful) {
            val list = response.body()
            if (list != null) {
                val data = list.data
                val listUrlImage = mutableListOf<Image>()
                data.forEach {
                    val image = it.images[0]
                    val type = image.type
                    if (type == "image/jpeg") {
                        listUrlImage.add(image)
                    }
                }
                withContext(Dispatchers.Main) {
                    images.postValue(listUrlImage)
                }


            }

        } else {
            erro.postValue("Erro Code ${response?.code()}")
        }

    }


}

class MainViewModelFactory(private val repository: ApiRepository) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            MainViewModel(this.repository) as T

        } else {
            throw IllegalArgumentException("ViewModel Not Found")
        }
    }
}