package com.example.messenger

import android.app.Application
import android.content.Context
import com.example.messenger.domain.di.components.AppComponent
import com.example.messenger.domain.di.components.DaggerAppComponent

class App: Application() {
    companion object {
        lateinit var appContext: Context
        lateinit var appComponent: AppComponent
    }

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.create()
        appContext = applicationContext
    }
}