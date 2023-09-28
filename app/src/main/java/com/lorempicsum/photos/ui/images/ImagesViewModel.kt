package com.lorempicsum.photos.ui.images

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.lorempicsum.photos.data.source.local.database.entity.AuthorEntity
import com.lorempicsum.photos.data.source.repository.ImageRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@OptIn(ExperimentalCoroutinesApi::class)
@HiltViewModel
class ImagesViewModel @Inject constructor(
    private val imageRepository: ImageRepository
) : ViewModel() {

    val selectedAuthor = imageRepository
        .selectedAuthor
        .map {
            it?.authorName
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = null
        )

    val images = selectedAuthor.flatMapLatest {
        imageRepository.getImages(it).cachedIn(viewModelScope)
    }

    fun updateSelectedAuthor(author: AuthorEntity?) {
        viewModelScope.launch {
            imageRepository.updateAuthorSelection(author)
        }
    }

    val authors = imageRepository
        .authors
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )
}
