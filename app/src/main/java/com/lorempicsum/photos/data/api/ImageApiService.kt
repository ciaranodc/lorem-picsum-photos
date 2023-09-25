package com.lorempicsum.photos.data.api

import com.lorempicsum.photos.data.source.database.entity.ImageEntity
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface ImageApiService {
    @GET("v2/list")
    suspend fun getImagesList(
        @Query("page") page: Int,
        @Query("limit") limit: Int,
    ): List<ImageEntity>

    companion object {
        private const val BASE_URL = "https://picsum.photos/"

        fun create(): ImageApiService {
            val imageApiService: ImageApiService by lazy {
                Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                    .create(ImageApiService::class.java)
            }

            return imageApiService
        }
    }
}