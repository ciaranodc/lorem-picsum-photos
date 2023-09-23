package com.lorempicsum.photos.data.source.database.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.lorempicsum.photos.data.source.database.entity.AuthorEntity

@Dao
interface AuthorDao {
    @Query("SELECT * FROM image")
    fun getAll(): List<AuthorEntity>

    @Upsert
    fun insertOrUpdateAll(images: List<AuthorEntity>)
}