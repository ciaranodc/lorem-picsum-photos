package com.transactcampus.assessment.data.api

import com.transactcampus.assessment.data.Image
import retrofit2.http.GET

interface ImagesRetrofitService {
    @GET("v2/list")
    suspend fun getImagesList(): List<Image>

    @GET("id/{id}/info")
    suspend fun getImageInfo(): Image
}