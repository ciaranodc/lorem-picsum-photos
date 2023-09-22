package com.lorempicsum.photos.data.source.repository

import android.util.Log
import com.lorempicsum.photos.data.Image
import com.lorempicsum.photos.data.Result
import com.lorempicsum.photos.data.api.RetrofitInstance

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