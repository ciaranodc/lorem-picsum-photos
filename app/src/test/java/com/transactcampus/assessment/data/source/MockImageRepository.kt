package com.transactcampus.assessment.data.source

import com.google.gson.Gson
import com.transactcampus.assessment.TestUtils
import com.transactcampus.assessment.data.Image
import com.transactcampus.assessment.data.Result


class MockImageRepository : ImageRepository {

    override suspend fun getImages(): Result<List<Image>> {
        val rawJson = TestUtils().getRawJsonFromFile("raw/json/images_response.json")
        val imagesList = Gson().fromJson(rawJson, Array<Image>::class.java).asList()

        return Result.Success(imagesList)
    }
}