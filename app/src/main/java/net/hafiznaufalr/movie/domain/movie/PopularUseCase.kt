package net.hafiznaufalr.movie.domain.movie

import net.hafiznaufalr.movie.data.movie.MovieRepository
import net.hafiznaufalr.movie.data.movie.model.MovieDataModel
import net.hafiznaufalr.movie.domain.base.BaseUseCase
import javax.inject.Inject

class PopularUseCase @Inject constructor(
    private val movieRepository: MovieRepository
) : BaseUseCase<MovieDataModel>() {

    fun addParam(page: Int) = apply {
        params = mapOf(
            KEY_PAGE to page
        )
    }

    override suspend fun build(): MovieDataModel {
        return movieRepository.getPopularMovie(params[KEY_PAGE] as Int)
    }

    companion object {
        private const val KEY_PAGE = "KEY_PAGE"
    }
}