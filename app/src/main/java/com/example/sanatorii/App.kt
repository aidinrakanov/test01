package com.example.sanatorii

import android.app.Application
import com.example.sanatorii.modules.appModule
import com.example.sanatorii.modules.repositoriesModule
import com.google.firebase.firestore.FirebaseFirestore
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App : Application() {
    companion object {
        lateinit var db: FirebaseFirestore
    }

    override fun onCreate() {
        super.onCreate()
        db = FirebaseFirestore.getInstance()

        startKoin {
            androidContext(this@App)
            modules(appModule)
            modules(repositoriesModule)
        }
    }
}