package net.hafiznaufalr.movie.data.movie

import net.hafiznaufalr.movie.data.movie.mapper.mapToModel
import net.hafiznaufalr.movie.data.movie.model.GenreModel
import net.hafiznaufalr.movie.data.movie.model.MovieDataModel
import net.hafiznaufalr.movie.data.movie.model.MovieReviewModel
import net.hafiznaufalr.movie.data.movie.remote.MovieApi
import javax.inject.Inject

interface MovieRepository {
    suspend fun getNowPlayingMovie(): MovieDataModel
    suspend fun getPopularMovie(page: Int): MovieDataModel
    suspend fun getMovieGenre(): List<GenreModel>
    suspend fun getMovieReviews(movieId: Int): List<MovieReviewModel>
    suspend fun getMovieTrailerKey(movieId: Int): String
}

class MovieRepositoryImpl @Inject constructor(
    private val movieApi: MovieApi
) : MovieRepository {
    override suspend fun getNowPlayingMovie(): MovieDataModel {
        return movieApi.getNowPlaying().mapToModel()
    }

    override suspend fun getPopularMovie(page: Int): MovieDataModel {
        return movieApi.getPopular(page = page).mapToModel()
    }

    override suspend fun getMovieGenre(): List<GenreModel> {
        return movieApi.getMovieGenre().mapToModel()
    }

    override suspend fun getMovieReviews(movieId: Int): List<MovieReviewModel> {
        return movieApi.getMovieReviews(movieId = movieId).results?.mapToModel() ?: throw Exception("data not found")
    }

    override suspend fun getMovieTrailerKey(movieId: Int): String {
        return movieApi.getMovieTrailer(movieId).results?.mapToModel() ?: ""
    }

}