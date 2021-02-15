package com.example.thaidai

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import io.paperdb.Paper
@HiltAndroidApp
class Application : Application() {
    override fun onCreate() {
        super.onCreate()
        // initializing paperdb
        Paper.init(this)
    }
}