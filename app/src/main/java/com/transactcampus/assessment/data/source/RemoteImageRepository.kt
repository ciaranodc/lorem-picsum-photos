package com.transactcampus.assessment.data.source

import android.util.Log
import com.transactcampus.assessment.data.Image
import com.transactcampus.assessment.data.Result
import com.transactcampus.assessment.data.api.RetrofitInstance

class RemoteImageRepository : ImageRepository {

    private val imagesRetrofitService = RetrofitInstance.imagesRetrofitService

    override suspend fun getImages(): Result<List<Image>> {
        val imagesList: List<Image>

        try {
            imagesList = imagesRetrofitService.getImagesList()
        } catch (e: Exception) {
            Log.e(TAG, e.message.toString())
            return Result.Error(e)
        }

        return Result.Success(imagesList)
    }

    companion object {
        val TAG: String = this::class.java.simpleName
    }
}