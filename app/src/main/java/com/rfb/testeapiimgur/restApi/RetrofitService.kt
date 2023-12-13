package com.rfb.testeapiimgur.restApi

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object RetrofitService {

    const val BASE_URL = "https://api.imgur.com/3/"

    private val okHttpClient = OkHttpClient.Builder()
        .writeTimeout(10, TimeUnit.SECONDS)
        .readTimeout(20, TimeUnit.SECONDS)
        .connectTimeout(20, TimeUnit.SECONDS)
        .addInterceptor(object : Interceptor {
            override fun intercept(chain: Interceptor.Chain): Response {
                val constructorRequest = chain.request().newBuilder()
                val request = constructorRequest.addHeader(
                    "Authorization", "Client-ID 1ceddedc03a5d71"
                ).build()
                return chain.proceed(request)
            }
        })
        .build()


    val retrofit: ImgurApi = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .client(okHttpClient)
        .build()
        .create(ImgurApi::class.java)

}