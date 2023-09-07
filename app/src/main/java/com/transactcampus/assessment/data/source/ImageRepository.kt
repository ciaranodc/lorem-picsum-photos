package com.transactcampus.assessment.data.source

import com.transactcampus.assessment.data.Image
import com.transactcampus.assessment.data.Result

interface ImageRepository {
    suspend fun getImages() : Result<List<Image>>
}