package com.rfb.testeapiimgur.repository

import com.rfb.testeapiimgur.restApi.RetrofitService

class ApiRepository(private val retrofitService: RetrofitService) {


    suspend fun getPhotos(q: String) = retrofitService.retrofit.getPhotos(q)


}