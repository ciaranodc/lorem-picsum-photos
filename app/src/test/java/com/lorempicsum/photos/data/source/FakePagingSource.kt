package com.lorempicsum.photos.data.source

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.lorempicsum.photos.data.source.local.database.entity.ImageEntity

class FakePagingSource(private val imagesList: List<ImageEntity>) :
    PagingSource<Int, ImageEntity>() {

    override suspend fun load(
        params: LoadParams<Int>
    ): LoadResult<Int, ImageEntity> {
        return LoadResult.Page(
            data = imagesList,
            prevKey = null,
            nextKey = null
        )
    }

    override fun getRefreshKey(state: PagingState<Int, ImageEntity>): Int {
        return 0
    }
}