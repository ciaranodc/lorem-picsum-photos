package com.transactcampus.assessment.ui.photos

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.liveData
import com.transactcampus.assessment.data.Image
import com.transactcampus.assessment.data.source.ImageRepository

class ImagesViewModel(private val imageRepository: ImageRepository) : ViewModel() {
    val images: LiveData<List<Image>> = liveData {
        val data = imageRepository.getImages()
        emit(data)
    }
}

class ImagesViewModelFactory(private val imageRepository: ImageRepository) :
    ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return ImagesViewModel(imageRepository) as T
    }
}
