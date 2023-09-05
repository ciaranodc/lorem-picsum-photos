package com.transactcampus.assessment.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.transactcampus.assessment.data.source.RemoteImageRepository
import com.transactcampus.assessment.ui.photos.ImagesViewModel
import com.transactcampus.assessment.ui.photos.ImagesViewModelFactory

class MainActivity : ComponentActivity() {

    private val imageRepository = RemoteImageRepository()
    private val imagesViewModel: ImagesViewModel by viewModels {
        ImagesViewModelFactory(imageRepository)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent { ImagesApp(imagesViewModel) }
    }
}

@Preview(showBackground = true)
@Composable
fun ImagesPreview() {
    ImagesApp(ImagesViewModel(RemoteImageRepository()))
}
