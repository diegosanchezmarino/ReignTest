package com.dsm.reigntestapp.di

import android.app.Application
import androidx.room.Room
import com.dsm.reigntestapp.repository.database.AppDatabase
import com.dsm.reigntestapp.repository.database.PostDao
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

val databaseModule = module {

    fun provideDatabase(application: Application): AppDatabase {
        return Room.databaseBuilder(application.applicationContext, AppDatabase::class.java, "postsDB")
            .fallbackToDestructiveMigration()
            .build()
    }

    fun provideCountriesDao(database: AppDatabase): PostDao {
        return database.postDao()
    }

    single { provideDatabase(androidApplication()) }
    single { provideCountriesDao(get()) }
}