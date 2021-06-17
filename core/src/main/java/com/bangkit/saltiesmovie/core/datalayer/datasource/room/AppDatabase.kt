package com.bangkit.saltiesmovie.core.datalayer.datasource.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.bangkit.saltiesmovie.core.datalayer.model.Movie

@Database(entities = arrayOf(Movie::class), version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun saltiesDao(): MyDao
}