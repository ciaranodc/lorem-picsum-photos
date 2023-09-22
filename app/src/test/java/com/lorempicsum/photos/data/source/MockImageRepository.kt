package com.lorempicsum.photos.data.source

import com.google.gson.Gson
import com.lorempicsum.photos.TestUtils
import com.lorempicsum.photos.data.Image
import com.lorempicsum.photos.data.Result
import com.lorempicsum.photos.data.source.repository.ImageRepository


class MockImageRepository : ImageRepository {

    override suspend fun getImages(): Result<List<Image>> {
        val rawJson = TestUtils().getRawJsonFromFile("raw/json/images_response.json")
        val imagesList = Gson().fromJson(rawJson, Array<Image>::class.java).asList()

        return Result.Success(imagesList)
    }
}