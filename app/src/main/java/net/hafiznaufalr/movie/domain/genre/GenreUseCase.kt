package net.hafiznaufalr.movie.domain.genre

import net.hafiznaufalr.movie.data.movie.MovieRepository
import net.hafiznaufalr.movie.data.movie.model.GenreModel
import net.hafiznaufalr.movie.domain.base.BaseUseCase
import javax.inject.Inject

class GenreUseCase @Inject constructor(
    private val movieRepository: MovieRepository
) : BaseUseCase<List<GenreModel>>() {

    override suspend fun build(): List<GenreModel> {
        return movieRepository.getMovieGenre()
    }
}