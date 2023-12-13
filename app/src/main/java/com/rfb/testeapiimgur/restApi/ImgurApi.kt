package com.rfb.testeapiimgur.restApi

import com.rfb.testeapiimgur.model.Data
import com.rfb.testeapiimgur.model.Imagens
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ImgurApi {

    @GET("gallery/search/")
    suspend fun getPhotos(@Query("q") q: String): Response<Imagens>

}