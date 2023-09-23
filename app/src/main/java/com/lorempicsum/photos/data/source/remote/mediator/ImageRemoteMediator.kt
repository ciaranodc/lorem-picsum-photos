@file:OptIn(ExperimentalPagingApi::class)

package com.lorempicsum.photos.data.source.remote.mediator

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import com.lorempicsum.photos.data.api.ImageApiService
import com.lorempicsum.photos.data.source.database.dao.ImageDao
import com.lorempicsum.photos.data.source.database.entity.ImageEntity

class ImageRemoteMediator(
    private val query: String?,
    private val imageDao: ImageDao,
    private val networkService: ImageApiService
) : RemoteMediator<Int, ImageEntity>() {

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, ImageEntity>
    ): MediatorResult {
        return MediatorResult.Success(
            endOfPaginationReached = true
        )
    }
}