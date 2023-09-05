package com.transactcampus.assessment.data.source

import com.transactcampus.assessment.data.Image
import com.transactcampus.assessment.data.api.RetrofitInstance

class RemoteImageRepository : ImageRepository {

    private val itemService = RetrofitInstance.itemService

    override suspend fun getImages(): List<Image> {
        return itemService.getImagesList()
    }
}