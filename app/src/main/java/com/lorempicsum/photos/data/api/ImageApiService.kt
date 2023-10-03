package com.lorempicsum.photos.data.api

import com.lorempicsum.photos.data.source.local.database.entity.ImageEntity
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface ImageApiService {
    @GET("list")
    suspend fun getImagesList(
        @Query("page") page: Int,
        @Query("limit") limit: Int,
    ): List<ImageEntity>

    companion object {
        private const val BASE_URL = "https://picsum.photos/v2/"

        fun create(): ImageApiService {
            val imageApiService: ImageApiService by lazy {
                val interceptor = HttpLoggingInterceptor()
                interceptor.level = HttpLoggingInterceptor.Level.BODY
                val client = OkHttpClient().newBuilder().addInterceptor(interceptor).build()

                Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(client)
                    .build()
                    .create(ImageApiService::class.java)
            }

            return imageApiService
        }
    }
}