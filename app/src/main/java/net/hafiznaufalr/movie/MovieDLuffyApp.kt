package net.hafiznaufalr.movie

import android.app.Application
import android.os.Build
import androidx.appcompat.app.AppCompatDelegate
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class MovieDLuffyApp: Application() {
    override fun onCreate() {
        super.onCreate()
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.P) AppCompatDelegate.setDefaultNightMode(
            AppCompatDelegate.MODE_NIGHT_NO)
    }
}