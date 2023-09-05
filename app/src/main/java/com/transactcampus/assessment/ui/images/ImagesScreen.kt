@file:OptIn(ExperimentalMaterial3Api::class)

package com.transactcampus.assessment.ui.images

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.transactcampus.assessment.data.Image
import com.transactcampus.assessment.ui.composables.CustomDropdownMenu

@Composable
fun ImagesScreen(imagesViewModel: ImagesViewModel) {

    val images by imagesViewModel.images.observeAsState(initial = emptyList())

    Scaffold(topBar = {
        TopAppBar(
            title = { Text("LoremPicsum") })
    }) { contentPadding ->
        Box(modifier = Modifier.padding(contentPadding)) {
            // Reversing photos list because first few photos are terrible but last few are lovely
            ImageScreenContent(images.reversed())
        }
    }
}

@Composable
fun ImageScreenContent(images: List<Image>) {
    val defaultSelection = "Select author..."
    val authors: List<String> = images.map { it.author }.distinct()

    var selectedAuthor by remember { mutableStateOf("Select author...") }

    val imagesList = if (selectedAuthor == defaultSelection) {
        images
    } else {
        images.filter { it.author == selectedAuthor }
    }

    Column {
        CustomDropdownMenu(
            menuItems = authors,
            defaultSelection = defaultSelection,
            onSelectAuthor = {
                selectedAuthor = it
            }
        )

        LazyVerticalGrid(
            columns = GridCells.Fixed(1),
        ) {

            items(
                items = imagesList,
                key = { it.id }
            ) { shopItem ->
                ImageCard(image = shopItem)
            }
        }
    }
}