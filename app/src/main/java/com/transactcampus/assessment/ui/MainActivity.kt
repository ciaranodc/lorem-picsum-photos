package com.transactcampus.assessment.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.transactcampus.assessment.ui.PhotosApp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent { PhotosApp() }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    PhotosApp()
}
