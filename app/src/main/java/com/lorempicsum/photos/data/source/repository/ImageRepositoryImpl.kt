@file:OptIn(ExperimentalPagingApi::class)

package com.lorempicsum.photos.data.source.repository

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.lorempicsum.photos.data.api.ImageApiService
import com.lorempicsum.photos.data.source.database.dao.ImageDao
import com.lorempicsum.photos.data.source.database.entity.AuthorEntity
import com.lorempicsum.photos.data.source.database.entity.ImageEntity
import com.lorempicsum.photos.data.source.remote.mediator.ImageRemoteMediator
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

private const val PAGE_SIZE = 10

class ImageRepositoryImpl @Inject constructor(
    private val imageDao: ImageDao,
    private val imageApiService: ImageApiService
) : ImageRepository {

    override fun getImages(query: String?): Flow<PagingData<ImageEntity>> {
        return Pager(
            config = PagingConfig(
                pageSize = PAGE_SIZE
            ),
            remoteMediator = ImageRemoteMediator(
                query,
                imageDao,
                imageApiService
            ),
            pagingSourceFactory = {
                if (query.isNullOrEmpty()) {
                    imageDao.getAll()
                } else {
                    imageDao.getImagesByAuthor(query)
                }
            }
        ).flow
    }

    override val selectedAuthor: Flow<AuthorEntity?>
        get() = TODO("Not yet implemented")
}