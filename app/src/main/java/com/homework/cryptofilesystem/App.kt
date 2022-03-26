package com.homework.cryptofilesystem

import android.app.Application
import android.content.Context

class App : Application() {

    init {
        instance = this
    }

    override fun onCreate() {
        super.onCreate()
        appContext = applicationContext
    }

    companion object {
        lateinit var appContext: Context
            private set
        lateinit var instance: App
            private set
    }
}
