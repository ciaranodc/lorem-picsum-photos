package com.transactcampus.assessment.ui.images

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.transactcampus.assessment.data.Image
import com.transactcampus.assessment.data.Result
import com.transactcampus.assessment.data.datastore.LocalUserPreferences
import com.transactcampus.assessment.data.datastore.LocalUserPreferences.Companion.SELECTED_AUTHOR_KEY
import com.transactcampus.assessment.data.datastore.UserPreferences
import com.transactcampus.assessment.data.source.ImageRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch

class ImagesViewModel(
    private val imageRepository: ImageRepository,
    private val userPreferences: UserPreferences
) : ViewModel() {
    private var _images = MutableLiveData<Result<List<Image>>>()
    val images: LiveData<Result<List<Image>>> = _images

    fun loadImages() {
        viewModelScope.launch {
            _images.value = imageRepository.getImages()
        }
    }

    private var selectedAuthorDataStoreFlow = userPreferences.getStoredData(SELECTED_AUTHOR_KEY)

    private var userSelectedAuthorFlow = MutableStateFlow(DEFAULT_AUTHOR_SELECTION)

    val selectedAuthor =
        selectedAuthorDataStoreFlow.combine(userSelectedAuthorFlow) { dataStore, memory ->
            if (memory != DEFAULT_AUTHOR_SELECTION) {
                return@combine memory
            } else if (dataStore.isNotBlank()) {
                return@combine dataStore
            } else {
                return@combine DEFAULT_AUTHOR_SELECTION
            }
        }

    private fun updateUserSelectedAuthor(author: String) {
        userSelectedAuthorFlow.value = author
    }

    fun saveAuthorSelection(author: String) {
        updateUserSelectedAuthor(author)

        viewModelScope.launch {
            try {
                userPreferences.storeData(SELECTED_AUTHOR_KEY, author)
            } catch (e: Exception) {
                Log.e("ImagesViewModel", e.message.toString())
            }
        }
    }
}

class ImagesViewModelFactory(
    private val imageRepository: ImageRepository,
    private val userPreferences: LocalUserPreferences
) :
    ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return ImagesViewModel(imageRepository, userPreferences) as T
    }
}
