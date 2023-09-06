package com.transactcampus.assessment.ui.images

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.transactcampus.assessment.data.Image
import com.transactcampus.assessment.data.datastore.AuthorPrefs
import com.transactcampus.assessment.data.source.ImageRepository
import kotlinx.coroutines.launch

class ImagesViewModel(
    private val imageRepository: ImageRepository,
    private val authorPrefs: AuthorPrefs
) : ViewModel() {
    val images: LiveData<List<Image>> = liveData {
        val data = imageRepository.getImages()
        emit(data)
    }

    val lastSelectedAuthor = authorPrefs.lastSelectedAuthor

    fun saveAuthorSelection(author: String) {
        viewModelScope.launch {
            try {
                authorPrefs.saveSelectedAuthor(author)
            } catch (e: Exception) {
//                Log.e(TAG, e.message.toString())
            }
        }
    }
}

class ImagesViewModelFactory(
    private val imageRepository: ImageRepository,
    private val authorPrefs: AuthorPrefs
) :
    ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return ImagesViewModel(imageRepository, authorPrefs) as T
    }
}
