package com.lorempicsum.photos.data.di

import android.content.Context
import androidx.room.Room
import com.lorempicsum.photos.data.source.local.database.ImageDatabase
import com.lorempicsum.photos.data.source.local.database.dao.ImageDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {

    @Provides
    @Singleton
    fun provideImageDatabase(@ApplicationContext appContext: Context): ImageDatabase {
        return Room.databaseBuilder(
            appContext,
            ImageDatabase::class.java,
            "image-database"
        ).build()
    }

    @Provides
    fun provideImageDao(appDatabase: ImageDatabase): ImageDao {
        return appDatabase.imageDao()
    }
}