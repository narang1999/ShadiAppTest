package com.example.shaditestapp

import android.app.Application
import com.example.shaditestapp.di.app_module
import com.example.shaditestapp.di.single_module
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class ShadiApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            modules(app_module + single_module)
            androidContext(this@ShadiApplication)
        }
    }
}