package com.lorempicsum.photos.ui

import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController
import com.lorempicsum.photos.ui.navigation.ImagesNavGraph
import com.lorempicsum.photos.ui.images.ImagesViewModel

@Composable
fun ImagesApp(imagesViewModel: ImagesViewModel) {
    val navController = rememberNavController()
    ImagesNavGraph(navController, imagesViewModel)
}
