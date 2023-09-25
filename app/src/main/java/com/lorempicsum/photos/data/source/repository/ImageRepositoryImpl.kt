@file:OptIn(ExperimentalPagingApi::class)

package com.lorempicsum.photos.data.source.repository

import android.util.Log
import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.room.withTransaction
import com.lorempicsum.photos.data.api.ImageApiService
import com.lorempicsum.photos.data.source.database.ImageDatabase
import com.lorempicsum.photos.data.source.database.entity.AuthorEntity
import com.lorempicsum.photos.data.source.database.entity.ImageEntity
import com.lorempicsum.photos.data.source.remote.mediator.ImageRemoteMediator
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import javax.inject.Inject

private const val PAGE_SIZE = 50

class ImageRepositoryImpl @Inject constructor(
    private val imageDatabase: ImageDatabase,
    private val imageApiService: ImageApiService
) : ImageRepository {

    override fun getImages(author: String?): Flow<PagingData<ImageEntity>> {
        return Pager(
            config = PagingConfig(
                pageSize = PAGE_SIZE,
                initialLoadSize = PAGE_SIZE
            ),
            remoteMediator = ImageRemoteMediator(
                author,
                imageDatabase,
                imageApiService
            ),
            pagingSourceFactory = {
                if (author.isNullOrEmpty()) {
                    imageDatabase.imageDao().getAllImages()
                } else {
                    imageDatabase.imageDao().getImagesByAuthor(author)
                }
            }
        ).flow
    }

    override suspend fun updateAuthorSelection(author: AuthorEntity?) {
        withContext(Dispatchers.IO) {
            try {
                val authorDao = imageDatabase.authorDao()

                imageDatabase.withTransaction {
                    val currentSelectedAuthor =
                        authorDao.getCurrentSelectedAuthor()?.copy(isSelected = false)

                    if (currentSelectedAuthor != null) {
                        authorDao.upsertAuthor(currentSelectedAuthor)
                    }

                    if (author != null) {
                        authorDao.upsertAuthor(author)
                    }
                }
            } catch (e: Exception) {
                Log.e("ImageRepositoryImpl", "Failed to update author selection: ${e.message}")
            }
        }
    }

    override val selectedAuthor: Flow<AuthorEntity?>
        get() = imageDatabase.authorDao().getCurrentSelectedAuthorFlow()
}