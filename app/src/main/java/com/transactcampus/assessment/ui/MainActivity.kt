package com.transactcampus.assessment.ui

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.datastore.preferences.preferencesDataStore
import com.transactcampus.assessment.data.datastore.LocalUserPreferences
import com.transactcampus.assessment.data.source.RemoteImageRepository
import com.transactcampus.assessment.ui.images.ImagesViewModel
import com.transactcampus.assessment.ui.images.ImagesViewModelFactory

class MainActivity : ComponentActivity() {
    private val Context.dataStore by preferencesDataStore(name = "SelectedAuthor")
    private val userPreferences: LocalUserPreferences by lazy {
        LocalUserPreferences(dataStore)
    }

    private val imageRepository = RemoteImageRepository()
    private val imagesViewModel: ImagesViewModel by viewModels {
        ImagesViewModelFactory(imageRepository, userPreferences)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent { ImagesApp(imagesViewModel) }
    }
}