package net.hafiznaufalr.movie.data.network

import android.content.Context
import com.chuckerteam.chucker.api.ChuckerInterceptor
import net.hafiznaufalr.movie.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import java.util.concurrent.TimeUnit

fun getBaseOkHttpClient(context: Context): OkHttpClient {
    val httpLoggingInterceptor = HttpLoggingInterceptor()
    if (BuildConfig.DEBUG) {
        httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
    } else {
        httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.NONE
    }

    val chucker = ChuckerInterceptor.Builder(context).build()

    return OkHttpClient.Builder()
        .addInterceptor(httpLoggingInterceptor)
        .addInterceptor(chucker)
        .connectTimeout(45, TimeUnit.SECONDS)
        .writeTimeout(45, TimeUnit.SECONDS)
        .retryOnConnectionFailure(true)
        .build()
}