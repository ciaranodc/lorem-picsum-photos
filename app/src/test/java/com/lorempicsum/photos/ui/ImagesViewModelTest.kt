package com.lorempicsum.photos.ui

import androidx.paging.testing.asSnapshot
import com.lorempicsum.photos.data.source.FakeImageRepository
import com.lorempicsum.photos.ui.images.ImagesViewModel
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test

class ImagesViewModelTest {

    private var repository = FakeImageRepository()
    private var viewModel = ImagesViewModel(repository)

    @Test
    fun whenImagesAreLoadedInViewModel_thenCorrectNumberOfImagesIsPresent() = runTest {
        val images = viewModel.images
        val imagesSnapshot = images.asSnapshot()

        assertEquals(30, imagesSnapshot.size)
    }
}