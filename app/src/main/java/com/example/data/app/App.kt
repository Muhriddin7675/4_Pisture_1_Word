package com.example.data.app

import android.app.Application
import com.example.domain.AppController

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        AppController.getInstance(this)
    }
}