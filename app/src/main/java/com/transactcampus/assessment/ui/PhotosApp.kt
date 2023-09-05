package com.transactcampus.assessment.ui

import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController
import com.transactcampus.assessment.ui.navigation.PhotosNavGraph

@Composable
fun PhotosApp() {
    val navController = rememberNavController()
    PhotosNavGraph(navController = navController)
}
