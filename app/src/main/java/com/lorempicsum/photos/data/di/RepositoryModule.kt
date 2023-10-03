package com.lorempicsum.photos.data.di

import com.lorempicsum.photos.data.source.repository.ImageRepository
import com.lorempicsum.photos.data.source.repository.ImageRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun provideImageRepository(impl: ImageRepositoryImpl): ImageRepository
}