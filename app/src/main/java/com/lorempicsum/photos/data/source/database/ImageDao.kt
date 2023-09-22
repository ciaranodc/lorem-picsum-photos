package com.lorempicsum.photos.data.source.database

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert

@Dao
interface ImageDao {
    @Query("SELECT * FROM image")
    fun getAll(): List<ImageEntity>

    @Upsert
    fun insertOrUpdateAll(images: List<ImageEntity>)
}