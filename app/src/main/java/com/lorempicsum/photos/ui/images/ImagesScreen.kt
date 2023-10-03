package com.lorempicsum.photos.ui.images

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.lorempicsum.photos.R
import com.lorempicsum.photos.data.source.local.database.entity.AuthorEntity
import com.lorempicsum.photos.data.source.local.database.entity.ImageEntity
import com.lorempicsum.photos.ui.composables.CentralProgressIndicator
import com.lorempicsum.photos.ui.composables.CustomDropdownMenu
import com.lorempicsum.photos.ui.composables.EmptyView
import com.lorempicsum.photos.ui.composables.ErrorRetryButton

@ExperimentalMaterial3Api
@Composable
fun ImagesScreen(imagesViewModel: ImagesViewModel) {
    val lastSelectedAuthor by imagesViewModel.selectedAuthor.collectAsStateWithLifecycle()
    val images = imagesViewModel.images.collectAsLazyPagingItems()
    val authors by imagesViewModel.authors.collectAsStateWithLifecycle()

    Scaffold(topBar = {
        TopAppBar(title = { Text(stringResource(R.string.app_bar_title)) })
    }) { contentPadding ->
        Box(modifier = Modifier.padding(contentPadding)) {
            ImageScreenContent(
                images,
                authors,
                lastSelectedAuthor ?: stringResource(R.string.select_author),
                onSelectAuthor = {
                    imagesViewModel.updateSelectedAuthor(it)
                })
        }
    }
}

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun ImageScreenContent(
    images: LazyPagingItems<ImageEntity>,
    authorsList: List<AuthorEntity>,
    currentAuthorSelection: String?,
    onSelectAuthor: (AuthorEntity?) -> Unit
) {
    Column {
        CustomDropdownMenu(menuItems = authorsList,
            initialSelection = currentAuthorSelection ?: stringResource(R.string.select_author),
            onSelectAuthor = {
                onSelectAuthor(it)
            })

        LazyColumn(
            modifier = Modifier.padding(top = 10.dp, bottom = 10.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp),
        ) {
            images.apply {
                when {
                    loadState.refresh is LoadState.Loading -> item {
                        CentralProgressIndicator(modifier = Modifier.fillParentMaxSize())
                    }

                    loadState.mediator?.refresh is LoadState.Error && images.itemCount == 0 -> {
                        item {
                            ErrorRetryButton(onClick = { retry() })
                        }
                    }

                    loadState.refresh is LoadState.NotLoading && images.itemCount == 0 -> {
                        item {
                            EmptyView(
                                modifier = Modifier
                                    .fillParentMaxSize()
                                    .padding(10.dp)
                            )
                        }
                    }

                    loadState.source.refresh is LoadState.NotLoading
                            || loadState.mediator?.refresh is LoadState.NotLoading -> {
                        items(count = images.itemCount) { index ->
                            images[index]?.let {
                                ImageCard(image = it)
                            }
                        }
                    }

                }
            }
        }
    }
}