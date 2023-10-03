package com.lorempicsum.photos.ui.navigation

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.lorempicsum.photos.ui.images.ImagesScreen
import com.lorempicsum.photos.ui.images.ImagesViewModel
import com.lorempicsum.photos.ui.navigation.AppDestinations.IMAGES_ROUTE

@OptIn(ExperimentalMaterial3Api::class)
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