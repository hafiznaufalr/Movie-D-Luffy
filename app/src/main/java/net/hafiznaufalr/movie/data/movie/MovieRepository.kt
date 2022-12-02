package net.hafiznaufalr.movie.data.movie

import net.hafiznaufalr.movie.data.movie.mapper.mapToModel
import net.hafiznaufalr.movie.data.movie.model.GenreModel
import net.hafiznaufalr.movie.data.movie.model.MovieDataModel
import net.hafiznaufalr.movie.data.movie.remote.MovieApi
import java.lang.Exception
import javax.inject.Inject

interface MovieRepository {
    suspend fun getNowPlayingMovie(): MovieDataModel
    suspend fun getPopularMovie(): MovieDataModel
    suspend fun getMovieGenre(): List<GenreModel>
}

class MovieRepositoryImpl @Inject constructor(
    private val movieApi: MovieApi
) : MovieRepository {
    override suspend fun getNowPlayingMovie(): MovieDataModel {
        return movieApi.getNowPlaying().mapToModel()
    }

    override suspend fun getPopularMovie(): MovieDataModel {
        return movieApi.getPopular().mapToModel()
    }

    override suspend fun getMovieGenre(): List<GenreModel> {
        return movieApi.getMovieGenre().mapToModel()
    }

}