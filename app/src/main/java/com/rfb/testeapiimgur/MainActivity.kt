package com.rfb.testeapiimgur

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.rfb.testeapiimgur.adapter.ApiAdapter
import com.rfb.testeapiimgur.databinding.ActivityMainBinding
import com.rfb.testeapiimgur.repository.ApiRepository
import com.rfb.testeapiimgur.restApi.RetrofitService
import com.rfb.testeapiimgur.viewModel.MainViewModel
import com.rfb.testeapiimgur.viewModel.MainViewModelFactory
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }
    private val retrofitService = RetrofitService

    private val viewModel: MainViewModel by lazy {
        ViewModelProvider(this, MainViewModelFactory(ApiRepository(retrofitService))
        )[MainViewModel::class.java]
    }
    private lateinit var recyclerView: RecyclerView
    private val adapter = ApiAdapter()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        callRecyclerView()
    }



    override fun onStart() {
        super.onStart()

        observer()


    }

    override fun onResume() {
        super.onResume()

        lifecycleScope.launch { viewModel.getPhotos("cats") }
    }

    private fun callRecyclerView() {
        recyclerView = binding.recycler
        recyclerView.layoutManager = GridLayoutManager(
            this, 3, RecyclerView.VERTICAL, false
        )
        recyclerView.setHasFixedSize(true)
        recyclerView.setItemViewCacheSize(20)
        recyclerView.adapter = adapter

    }

    private fun observer() {
        viewModel.images.observe(this) {
            adapter.submitList(it)
        }

        viewModel.erro.observe(this) {
            Toast.makeText(this, it, Toast.LENGTH_SHORT).show()

        }



    }


}