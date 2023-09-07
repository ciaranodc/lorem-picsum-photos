package com.transactcampus.assessment.ui

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.transactcampus.assessment.MainDispatcherRule
import com.transactcampus.assessment.data.Result
import com.transactcampus.assessment.data.datastore.MockUserPreferences
import com.transactcampus.assessment.data.source.MockImageRepository
import com.transactcampus.assessment.ui.images.ImagesViewModel
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner


@RunWith(MockitoJUnitRunner::class)
class ImagesViewModelTest {
    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private val viewModel = ImagesViewModel(
        imageRepository = MockImageRepository(),
        userPreferences = MockUserPreferences()
    )

    @Before
    fun loadViewModelImages() {
        viewModel.loadImages()
    }

    @Test
    fun whenImagesAreLoadedInViewModel_thenLiveDataIsResultTypeSuccess() {
        assertTrue(viewModel.images.value?.isSuccess() == true)
    }

    @Test
    fun whenImagesAreLoadedInViewModel_thenCorrectNumberOfImagesIsPresent() {
        assertEquals(30, (viewModel.images.value as? Result.Success)?.data?.size)
    }
}