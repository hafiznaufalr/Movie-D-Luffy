package net.hafiznaufalr.movie.data.preferences

import android.content.SharedPreferences
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import net.hafiznaufalr.movie.data.movie.model.GenreModel

object Preferences {
    private const val KEY_GENRES = "KEY_GENRES"
    private lateinit var preferences: SharedPreferences

    fun initialize(prefs: SharedPreferences) {
        preferences = prefs
    }

    fun putGenres(genres: Any) {
        val editor = preferences.edit()
        editor.putString(KEY_GENRES, Gson().toJson(genres))
        editor.apply()
    }

    fun getGenres(): List<GenreModel>? {
        return try {
            val itemType = object : TypeToken<List<GenreModel>>() {}.type
            Gson().fromJson<List<GenreModel>>(
                preferences.getString(KEY_GENRES, null),
                itemType
            )
        } catch (e: Exception) {
            null
        }
    }
}