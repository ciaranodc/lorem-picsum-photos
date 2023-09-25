package com.lorempicsum.photos.data.source.repository

import androidx.paging.PagingData
import com.lorempicsum.photos.data.source.database.entity.AuthorEntity
import com.lorempicsum.photos.data.source.database.entity.ImageEntity
import kotlinx.coroutines.flow.Flow

interface ImageRepository {
    fun getImages(author: String? = null) : Flow<PagingData<ImageEntity>>

    suspend fun updateAuthorSelection(author: AuthorEntity?)

    val selectedAuthor: Flow<AuthorEntity?>
}