package net.hafiznaufalr.movie.domain.movie

import net.hafiznaufalr.movie.data.movie.MovieRepository
import net.hafiznaufalr.movie.data.movie.model.MovieDataModel
import net.hafiznaufalr.movie.domain.base.BaseUseCase
import javax.inject.Inject

class NowPlayingUseCase @Inject constructor(
    private val movieRepository: MovieRepository
): BaseUseCase<MovieDataModel>() {

    override suspend fun build(): MovieDataModel {
        return movieRepository.getNowPlayingMovie()
    }
}