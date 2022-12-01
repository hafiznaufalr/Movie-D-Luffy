package net.hafiznaufalr.movie.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import net.hafiznaufalr.movie.data.movie.MovieRepository
import net.hafiznaufalr.movie.data.movie.MovieRepositoryImpl
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {

    @Provides
    @Singleton
    fun provideMovieRepository(
        movieRepositoryImpl: MovieRepositoryImpl
    ): MovieRepository = movieRepositoryImpl
}