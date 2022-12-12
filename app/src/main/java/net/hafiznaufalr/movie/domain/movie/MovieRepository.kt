package net.hafiznaufalr.movie.domain.movie

import net.hafiznaufalr.movie.domain.movie.model.GenreModel
import net.hafiznaufalr.movie.domain.movie.model.MovieDataModel
import net.hafiznaufalr.movie.domain.movie.model.MovieReviewModel

interface MovieRepository {
    suspend fun getNowPlayingMovie(): MovieDataModel
    suspend fun getPopularMovie(page: Int): MovieDataModel
    suspend fun getMovieGenre(): List<GenreModel>
    suspend fun getMovieReviews(movieId: Int): List<MovieReviewModel>
    suspend fun getMovieTrailerKey(movieId: Int): String
}
