package net.hafiznaufalr.movie.domain.movie.usecase

import net.hafiznaufalr.movie.domain.movie.model.MovieDataModel
import net.hafiznaufalr.movie.domain.base.BaseUseCase
import net.hafiznaufalr.movie.domain.movie.MovieRepository
import javax.inject.Inject

class NowPlayingUseCase @Inject constructor(
    private val movieRepository: MovieRepository
): BaseUseCase<MovieDataModel>() {

    override suspend fun build(): MovieDataModel {
        return movieRepository.getNowPlayingMovie()
    }
}