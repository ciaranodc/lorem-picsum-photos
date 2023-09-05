package com.transactcampus.assessment.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.transactcampus.assessment.ui.photos.PhotosScreen

@Composable
fun PhotosNavGraph(
    navController: NavHostController
) {
    NavHost(navController = navController, startDestination = "photos") {
        composable("photos") {
            PhotosScreen()
        }
    }
}