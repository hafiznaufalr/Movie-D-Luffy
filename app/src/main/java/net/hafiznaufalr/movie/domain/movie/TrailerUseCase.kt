package net.hafiznaufalr.movie.domain.movie

import net.hafiznaufalr.movie.data.movie.MovieRepository
import net.hafiznaufalr.movie.domain.base.BaseUseCase
import javax.inject.Inject

class TrailerUseCase @Inject constructor(
    private val movieRepository: MovieRepository
) : BaseUseCase<String>() {

    fun addParam(movieId: Int) = apply {
        params = mapOf(
            KEY_MOVIE_ID to movieId
        )
    }

    override suspend fun build(): String {
        return movieRepository.getMovieTrailerKey(params[KEY_MOVIE_ID] as Int)
    }

    companion object {
        private const val KEY_MOVIE_ID = "KEY_MOVIE_ID"
    }
}