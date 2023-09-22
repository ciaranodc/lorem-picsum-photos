package com.lorempicsum.photos.data

import com.google.gson.annotations.SerializedName

data class Image(
    val id: String,
    val author: String,
    val width: Int,
    val height: Int,
    val url: String,
    @SerializedName("download_url")
    val downloadUrl: String
)