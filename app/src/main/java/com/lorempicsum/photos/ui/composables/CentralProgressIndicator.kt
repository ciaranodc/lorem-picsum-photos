package com.lorempicsum.photos.ui.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun CentralProgressIndicator(
    modifier: Modifier = Modifier
) {
    Box (
        modifier = modifier
    ) {
        CircularProgressIndicator(
            modifier = Modifier
                .padding(10.dp)
                .align(Alignment.Center)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun CentralProgressIndicatorPreview() {
    CentralProgressIndicator(
        modifier = Modifier.fillMaxSize()
    )
}