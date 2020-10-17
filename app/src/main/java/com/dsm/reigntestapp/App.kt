package com.dsm.reigntestapp

import android.app.Application
import com.dsm.reigntestapp.di.appModule
import com.dsm.reigntestapp.di.databaseModule
import com.dsm.reigntestapp.di.retrofitModule
import com.facebook.stetho.Stetho
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.androidx.fragment.koin.fragmentFactory
import org.koin.core.context.startKoin
import timber.log.Timber

class App: Application() {



    override fun onCreate() {
        super.onCreate()

        Timber.plant(Timber.DebugTree())

        Stetho.initializeWithDefaults(this);
        startKoin{
            fragmentFactory()
            androidLogger()
            androidContext(this@App)
            modules(listOf(appModule, retrofitModule, databaseModule))
        }
    }

}