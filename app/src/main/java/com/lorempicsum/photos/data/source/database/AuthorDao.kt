package com.lorempicsum.photos.data.source.database

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert

@Dao
interface AuthorDao {
    @Query("SELECT * FROM image")
    fun getAll(): List<AuthorEntity>

    @Upsert
    fun insertOrUpdateAll(images: List<AuthorEntity>)
}