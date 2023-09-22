package com.lorempicsum.photos.data.source.repository

import com.lorempicsum.photos.data.Image
import com.lorempicsum.photos.data.Result

interface ImageRepository {
    suspend fun getImages() : Result<List<Image>>
}