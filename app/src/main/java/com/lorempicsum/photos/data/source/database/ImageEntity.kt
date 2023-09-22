package com.lorempicsum.photos.data.source.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "image")
data class ImageEntity(
    @PrimaryKey
    @ColumnInfo("id")
    val id: String,
    @ColumnInfo("author")
    val author: String,
    @ColumnInfo("width")
    val width: Int,
    @ColumnInfo("height")
    val height: Int,
    @ColumnInfo("url")
    val url: String,
    @SerializedName("download_url")
    @ColumnInfo("download_url")
    val downloadUrl: String
)