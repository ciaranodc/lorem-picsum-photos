package com.lorempicsum.photos.data.source.local.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "author")
data class AuthorEntity(
    @PrimaryKey
    @ColumnInfo("author_name")
    val authorName: String,
    @ColumnInfo("is_selected")
    val isSelected: Boolean = false,
)