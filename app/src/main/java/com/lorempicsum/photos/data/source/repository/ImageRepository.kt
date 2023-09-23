package com.lorempicsum.photos.data.source.repository

import androidx.paging.PagingData
import com.lorempicsum.photos.data.source.database.entity.AuthorEntity
import com.lorempicsum.photos.data.source.database.entity.ImageEntity
import kotlinx.coroutines.flow.Flow

interface ImageRepository {
    fun getImages(query: String? = null) : Flow<PagingData<ImageEntity>>

    val selectedAuthor: Flow<AuthorEntity?>
}