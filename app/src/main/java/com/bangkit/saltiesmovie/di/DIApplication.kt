package com.bangkit.saltiesmovie.di

import android.app.Application
import androidx.multidex.MultiDexApplication
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class DIApplication : MultiDexApplication() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@DIApplication)
            modules(listOf(StorageModul.storageModule, StorageModul.networkModule, StorageModul.DBModule))
        }
    }
}