package com.josh25.iristouchsecurenotes

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class IrisTouchApp:Application() {
    override fun onCreate() {
        super.onCreate()
    }
}