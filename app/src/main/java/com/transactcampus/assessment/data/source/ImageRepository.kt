package com.transactcampus.assessment.data.source

import com.transactcampus.assessment.data.Image

interface ImageRepository {
    suspend fun getImages() : List<Image>
}