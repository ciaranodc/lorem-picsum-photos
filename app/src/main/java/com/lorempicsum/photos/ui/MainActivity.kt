package com.lorempicsum.photos.ui

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.datastore.preferences.preferencesDataStore
import com.lorempicsum.photos.data.source.datastore.LocalUserPreferences
import com.lorempicsum.photos.data.source.repository.RemoteImageRepository
import com.lorempicsum.photos.ui.images.ImagesViewModel
import com.lorempicsum.photos.ui.images.ImagesViewModelFactory

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