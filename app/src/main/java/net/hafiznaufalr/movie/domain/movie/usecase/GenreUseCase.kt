package net.hafiznaufalr.movie.domain.movie.usecase

import net.hafiznaufalr.movie.domain.movie.model.GenreModel
import net.hafiznaufalr.movie.domain.base.BaseUseCase
import net.hafiznaufalr.movie.domain.movie.MovieRepository
import javax.inject.Inject

class GenreUseCase @Inject constructor(
    private val movieRepository: MovieRepository
) : BaseUseCase<List<GenreModel>>() {

    override suspend fun build(): List<GenreModel> {
        return movieRepository.getMovieGenre()
    }
}