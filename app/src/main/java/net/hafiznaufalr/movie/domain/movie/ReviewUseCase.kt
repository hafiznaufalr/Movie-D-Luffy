package net.hafiznaufalr.movie.domain.movie

import net.hafiznaufalr.movie.data.movie.MovieRepository
import net.hafiznaufalr.movie.data.movie.model.MovieReviewModel
import net.hafiznaufalr.movie.domain.base.BaseUseCase
import javax.inject.Inject

class ReviewUseCase @Inject constructor(
    private val movieRepository: MovieRepository
) : BaseUseCase<List<MovieReviewModel>>() {

    fun addParam(movieId: Int) = apply {
        params = mapOf(
            KEY_MOVIE_ID to movieId
        )
    }

    override suspend fun build(): List<MovieReviewModel> {
        return movieRepository.getMovieReviews(params[KEY_MOVIE_ID] as Int)
    }

    companion object {
        private const val KEY_MOVIE_ID = "KEY_MOVIE_ID"
    }
}