package com.transactcampus.assessment.ui

import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController
import com.transactcampus.assessment.ui.navigation.ImagesNavGraph
import com.transactcampus.assessment.ui.images.ImagesViewModel

@Composable
fun ImagesApp(imagesViewModel: ImagesViewModel) {
    val navController = rememberNavController()
    ImagesNavGraph(navController, imagesViewModel)
}
