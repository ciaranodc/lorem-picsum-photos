package com.lorempicsum.photos.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.lorempicsum.photos.ui.navigation.AppDestinations.IMAGES_ROUTE
import com.lorempicsum.photos.ui.images.ImagesScreen
import com.lorempicsum.photos.ui.images.ImagesViewModel

@Composable
fun ImagesNavGraph(
    navController: NavHostController,
    imagesViewModel: ImagesViewModel
) {
    NavHost(navController = navController, startDestination = IMAGES_ROUTE) {
        composable(IMAGES_ROUTE) {
            ImagesScreen(imagesViewModel)
        }
    }
}