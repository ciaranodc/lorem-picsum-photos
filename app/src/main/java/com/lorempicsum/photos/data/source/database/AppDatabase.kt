package com.lorempicsum.photos.data.source.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [ImageEntity::class, AuthorEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun imageDao(): ImageDao
}