package com.app.androidev.app

import android.app.Application
import android.content.Context
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class AndroidevApp : Application() {

    companion object {
        lateinit var applicationInstance: AndroidevApp

        val appContext: Context by lazy {
            applicationInstance.applicationContext
        }
    }

    override fun onCreate() {
        super.onCreate()
        applicationInstance = this
    }
}