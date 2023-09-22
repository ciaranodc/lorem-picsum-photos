package com.lorempicsum.photos.data.api

import com.lorempicsum.photos.data.Image
import retrofit2.http.GET

interface ImagesRetrofitService {
    @GET("v2/list")
    suspend fun getImagesList(): List<Image>
}