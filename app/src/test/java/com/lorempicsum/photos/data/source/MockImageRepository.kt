package com.lorempicsum.photos.data.source

import androidx.paging.PagingData
import com.google.gson.Gson
import com.lorempicsum.photos.TestUtils
import com.lorempicsum.photos.data.Image
import com.lorempicsum.photos.data.Result
import com.lorempicsum.photos.data.source.local.database.entity.AuthorEntity
import com.lorempicsum.photos.data.source.local.database.entity.ImageEntity
import com.lorempicsum.photos.data.source.repository.ImageRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow


class MockImageRepository : ImageRepository {
    override fun getImages(author: String?): Flow<PagingData<ImageEntity>> {
        val rawJson = TestUtils().getRawJsonFromFile("raw/json/images_response.json")
        val imagesList = Gson().fromJson(rawJson, Array<Image>::class.java).asList()

        //todo: return imagesList as flow of PagingData
        return MutableStateFlow(PagingData.empty())
    }

    override suspend fun updateAuthorSelection(author: AuthorEntity?) {
        TODO("Not yet implemented")
    }

    override val selectedAuthor: Flow<AuthorEntity?>
        get() = TODO("Not yet implemented")
}