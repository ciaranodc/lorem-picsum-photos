package com.lorempicsum.photos.ui.images

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.lorempicsum.photos.data.source.database.entity.AuthorEntity
import com.lorempicsum.photos.data.source.repository.ImageRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ImagesViewModel @Inject constructor(
    private val imageRepository: ImageRepository
) : ViewModel() {

    val images = imageRepository.getImages().cachedIn(viewModelScope)

    fun updateSelectedAuthor(author: AuthorEntity?) {
        viewModelScope.launch {
            imageRepository.updateAuthorSelection(author)
        }
    }

    val selectedAuthor = imageRepository
        .selectedAuthor
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = null
        )

//    private var userSelectedAuthorFlow = MutableStateFlow(DEFAULT_AUTHOR_SELECTION)
//
//    val selectedAuthor = userSelectedAuthorFlow
//
//    private fun updateUserSelectedAuthor(author: String) {
//        userSelectedAuthorFlow.value = author
//    }
//
//    fun saveAuthorSelection(author: String) {
//        updateUserSelectedAuthor(author)
//
//        //todo: save to db
//    }
}

//class ImagesViewModelFactory @Inject constructor(
//    private val imagesRepository: ImagesRepository,
//) :
//    ViewModelProvider.NewInstanceFactory() {
//    override fun <T : ViewModel> create(modelClass: Class<T>): T {
//        return ImagesViewModel(imagesRepository) as T
//    }
//}
