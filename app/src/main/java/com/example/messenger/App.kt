package com.example.messenger

import android.app.Application
import android.content.Context

class App: Application() {
    companion object {
        lateinit var appContext: Context
    }

    override fun onCreate() {
        appContext = applicationContext
        super.onCreate()
    }
}