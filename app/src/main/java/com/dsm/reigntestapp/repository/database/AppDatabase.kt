package com.dsm.reigntestapp.repository.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.dsm.reigntestapp.model.Post

@Database(entities = [Post::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun postDao(): PostDao

}
