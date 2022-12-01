package net.hafiznaufalr.movie.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import net.hafiznaufalr.movie.BuildConfig
import net.hafiznaufalr.movie.data.movie.remote.MovieApi
import retrofit2.Retrofit
import retrofit2.create
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ApiModule {

    @Provides
    @Singleton
    fun provideMovieApi(
        retrofit: Retrofit.Builder
    ): MovieApi = retrofit
        .baseUrl(BuildConfig.BASE_URL)
        .build()
        .create()
}