package com.lorempicsum.photos.data.source.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "author")
data class AuthorEntity(
    @PrimaryKey
    @ColumnInfo("id")
    val id: String,
    @ColumnInfo("author")
    val author: String,
    @ColumnInfo("is_selected")
    val isSelected: Boolean = false,
)