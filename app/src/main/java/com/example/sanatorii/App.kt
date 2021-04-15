package com.example.sanatorii

import android.app.Application
import com.example.sanatorii.modules.appModule
import com.example.sanatorii.modules.repositoriesModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@App)
            modules(appModule)
            modules(repositoriesModule)
        }
    }
}