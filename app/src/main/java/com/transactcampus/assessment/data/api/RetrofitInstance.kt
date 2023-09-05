package com.transactcampus.assessment.data.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {
    private const val BASE_URL = "https://picsum.photos/"

    private val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val itemService: ImagesRetrofitService by lazy {
        retrofit.create(ImagesRetrofitService::class.java)
    }
}