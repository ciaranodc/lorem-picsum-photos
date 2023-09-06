package com.transactcampus.assessment.data.source

import android.util.Log
import com.transactcampus.assessment.data.Image
import com.transactcampus.assessment.data.api.RetrofitInstance

class RemoteImageRepository : ImageRepository {

    private val itemService = RetrofitInstance.itemService

    override suspend fun getImages(): List<Image> {
        var imagesList = emptyList<Image>()

        try {
            imagesList = itemService.getImagesList()
        } catch (e: Exception) {
            Log.e(TAG, e.message.toString())
        }

        return imagesList
    }

    companion object {
        val TAG: String = this::class.java.simpleName
    }
}