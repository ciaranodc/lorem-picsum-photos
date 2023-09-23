package com.lorempicsum.photos.data.source.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.lorempicsum.photos.data.source.database.dao.ImageDao
import com.lorempicsum.photos.data.source.database.entity.AuthorEntity
import com.lorempicsum.photos.data.source.database.entity.ImageEntity

@Database(entities = [ImageEntity::class, AuthorEntity::class], version = 1)
abstract class ImageDatabase : RoomDatabase() {
    abstract fun imageDao(): ImageDao
}