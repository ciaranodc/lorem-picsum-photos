package com.lorempicsum.photos.data.source.local.database.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.lorempicsum.photos.data.source.local.database.entity.AuthorEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface AuthorDao {
    @Query("SELECT * FROM author")
    fun getAllAuthors(): List<AuthorEntity>

    @Query("SELECT * FROM author where is_selected = 1 LIMIT 1")
    fun getCurrentSelectedAuthorFlow(): Flow<AuthorEntity?>

    @Query("SELECT * FROM author where is_selected = 1 LIMIT 1")
    fun getCurrentSelectedAuthor(): AuthorEntity?

    @Upsert
    fun upsertAuthors(authors: List<AuthorEntity>)

    @Upsert
    fun upsertAuthor(author: AuthorEntity)
}