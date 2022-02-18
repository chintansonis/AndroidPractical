package com.example.chintanandroidpractical

import android.app.Application
import dagger.hilt.android.HiltAndroidApp


// Init the HiltAndroid App instance
@HiltAndroidApp
class MainApp : Application() {

    override fun onCreate() {
        super.onCreate()
    }
}