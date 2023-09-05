@file:OptIn(ExperimentalMaterial3Api::class)

package com.transactcampus.assessment.ui.images

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.transactcampus.assessment.data.Image

@Composable
fun ImagesScreen(imagesViewModel: ImagesViewModel) {

    val images by imagesViewModel.images.observeAsState(initial = emptyList())

    Scaffold(topBar = {
        TopAppBar(
            title = { Text("LoremPicsum") })
    }) { contentPadding ->
        Box(modifier = Modifier.padding(contentPadding)) {
            ImageScreenContent(images)
        }
    }
}

@Composable
fun ImageScreenContent(images: List<Image>) {
    val defaultSelection = "Select author..."
    val authors: List<String> = images.map { it.author }.distinct()
    var isExpanded by remember { mutableStateOf(false) }
    var selectedText by remember { mutableStateOf("Select author...") }

    var imagesList = if (selectedText == defaultSelection) {
        images.reversed()
    } else {
        images.reversed().filter { it.author == selectedText }
    }

    Column {
//        CustomDropdownMenu(
//            menuItems = authors,
//            defaultSelection = "Select author..."
//        )

        Row(
            modifier = Modifier
                .padding(horizontal = 10.dp),
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            ExposedDropdownMenuBox(
                modifier = Modifier
                    .weight(1f),
                expanded = isExpanded,
                onExpandedChange = {
                    isExpanded = !isExpanded
                }
            ) {
                TextField(
                    value = selectedText,
                    onValueChange = {},
                    readOnly = true,
                    trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = isExpanded) },
                    modifier = Modifier.menuAnchor()
                )
                ExposedDropdownMenu(

                    expanded = isExpanded,
                    onDismissRequest = { isExpanded = false }) {
                    authors.forEach { item ->
                        DropdownMenuItem(text = {
                            Text(text = item)
                        }, onClick = {
                            selectedText = item
                            isExpanded = false
                        })
                    }
                }
            }

            Button(
                shape = RoundedCornerShape(10),
                onClick = {
                    selectedText = "Select author..."
                }
            ) {
                Text(text = "CLEAR")
            }
        }

        LazyVerticalGrid(
            columns = GridCells.Fixed(1),
        ) {

            items(
                // reverse photos list because first few photos are terrible but last few are lovely
                items = imagesList,
                key = { it.id }
            ) { shopItem ->
                ImageCard(image = shopItem)
            }
        }
    }
}