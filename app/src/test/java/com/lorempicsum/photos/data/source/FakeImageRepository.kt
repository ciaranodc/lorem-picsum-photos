package com.lorempicsum.photos.data.source

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.google.gson.Gson
import com.lorempicsum.photos.TestUtils
import com.lorempicsum.photos.data.source.local.database.entity.AuthorEntity
import com.lorempicsum.photos.data.source.local.database.entity.ImageEntity
import com.lorempicsum.photos.data.source.repository.ImageRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow

class FakeImageRepository : ImageRepository {
    override fun getImages(author: String?): Flow<PagingData<ImageEntity>> {
        val rawJson = TestUtils().getRawJsonFromFile("raw/json/images_response.json")
        val imagesList = Gson().fromJson(rawJson, Array<ImageEntity>::class.java).asList()

        return Pager(
            PagingConfig(pageSize = 20)
        ) {
            FakePagingSource(imagesList)
        }.flow
    }

    override suspend fun updateAuthorSelection(author: AuthorEntity?) {
        //do nothing
    }

    override val authors: Flow<List<AuthorEntity>>
        get() = emptyFlow()

    override val selectedAuthor: Flow<AuthorEntity?>
        get() = emptyFlow()
}