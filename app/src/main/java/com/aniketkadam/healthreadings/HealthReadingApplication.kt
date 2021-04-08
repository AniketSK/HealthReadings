package com.aniketkadam.healthreadings

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import io.realm.Realm

@HiltAndroidApp
class HealthReadingApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        Realm.init(this)
    }
}