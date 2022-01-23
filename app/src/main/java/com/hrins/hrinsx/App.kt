package com.hrins.hrinsx

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class App :Application(){
    override fun onCreate() {
        super.onCreate()

        instance = this

    }

    companion object {
        @JvmStatic
        lateinit var instance: App
            private set
    }
}