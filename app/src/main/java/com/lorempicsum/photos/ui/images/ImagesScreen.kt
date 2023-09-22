@file:OptIn(ExperimentalMaterial3Api::class)

package com.lorempicsum.photos.ui.images

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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.lorempicsum.photos.R
import com.lorempicsum.photos.data.Image
import com.lorempicsum.photos.data.Result
import com.lorempicsum.photos.ui.composables.CentralProgressIndicator
import com.lorempicsum.photos.ui.composables.CustomDropdownMenu
import com.lorempicsum.photos.ui.composables.ErrorRetryButton

const val DEFAULT_AUTHOR_SELECTION = "Select author..."

@Composable
fun ImagesScreen(imagesViewModel: ImagesViewModel) {
    val imagesResult by imagesViewModel.images.observeAsState(initial = Result.Loading)
    val lastSelectedAuthor by imagesViewModel.selectedAuthor.collectAsState(initial = DEFAULT_AUTHOR_SELECTION)

    LaunchedEffect(Unit) {
        imagesViewModel.loadImages()
    }

    if (imagesResult.isSuccess()) {
        val images = (imagesResult as Result.Success).data
        val authorsList = images.map { it.author }.distinct()
        val imagesToShow = if (lastSelectedAuthor == DEFAULT_AUTHOR_SELECTION) {
            images.reversed() // reversing list because photos at the end are nicer!
        } else {
            images.filter { it.author == lastSelectedAuthor }
        }

        Scaffold(topBar = {
            TopAppBar(title = { Text(stringResource(R.string.app_bar_title)) })
        }) { contentPadding ->
            Box(modifier = Modifier.padding(contentPadding)) {
                // Reversing photos list because first few photos are terrible but last few are lovely
                ImageScreenContent(imagesToShow,
                    authorsList,
                    lastSelectedAuthor,
                    onSelectAuthor = { author ->
                        imagesViewModel.saveAuthorSelection(author)
                    })
            }
        }
    } else if (imagesResult.isError()) {
        ErrorRetryButton(onClick = {
            imagesViewModel.loadImages()
        })
    } else {
        CentralProgressIndicator()
    }
}

@Composable
fun ImageScreenContent(
    images: List<Image>,
    authorsList: List<String>,
    currentAuthorSelection: String,
    onSelectAuthor: (String) -> Unit
) {
    Column {
        CustomDropdownMenu(menuItems = authorsList,
            initialSelection = currentAuthorSelection,
            onSelectAuthor = {
                onSelectAuthor(it)
            })

        LazyVerticalGrid(
            columns = GridCells.Fixed(1),
        ) {

            items(items = images, key = { it.id }) { shopItem ->
                ImageCard(image = shopItem)
            }
        }
    }
}