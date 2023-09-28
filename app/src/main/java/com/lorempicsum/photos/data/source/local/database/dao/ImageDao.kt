package com.lorempicsum.photos.data.source.local.database.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.lorempicsum.photos.data.source.local.database.entity.ImageEntity

@Dao
interface ImageDao {
    @Query("SELECT * FROM image")
    fun getAllImages(): PagingSource<Int, ImageEntity>

    @Query("SELECT * FROM image WHERE author IS :author")
    fun getImagesByAuthor(author: String): PagingSource<Int, ImageEntity>

    /**
     * Insert images if they don't exist or update them if they do
     */
    @Upsert
    fun upsertAll(images: List<ImageEntity>)

    @Query("DELETE FROM image")
    suspend fun clearAll()
}