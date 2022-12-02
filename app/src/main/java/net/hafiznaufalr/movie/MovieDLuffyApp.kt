package net.hafiznaufalr.movie

import android.app.Application
import android.os.Build
import androidx.appcompat.app.AppCompatDelegate
import dagger.hilt.android.HiltAndroidApp
import net.hafiznaufalr.movie.data.preferences.Preferences

@HiltAndroidApp
class MovieDLuffyApp: Application() {
    override fun onCreate() {
        super.onCreate()

        val preferences = applicationContext.getSharedPreferences(applicationContext.packageName, MODE_PRIVATE)
        Preferences.initialize(preferences)

        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.P) AppCompatDelegate.setDefaultNightMode(
            AppCompatDelegate.MODE_NIGHT_NO)
    }
}