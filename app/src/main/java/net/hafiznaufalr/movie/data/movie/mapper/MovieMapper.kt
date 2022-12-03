package net.hafiznaufalr.movie.data.movie.mapper

import net.hafiznaufalr.movie.data.movie.model.MovieDataModel
import net.hafiznaufalr.movie.data.movie.model.MovieModel
import net.hafiznaufalr.movie.data.movie.response.BaseResponse
import net.hafiznaufalr.movie.data.movie.response.MovieResponse
import net.hafiznaufalr.movie.data.preferences.Preferences

fun BaseResponse<List<MovieResponse>>.mapToModel(): MovieDataModel {
    val dataModel = arrayListOf<MovieModel>()
    val genres = Preferences.getGenres()

    this.results?.map {
        val genreString = arrayListOf<String>()

        it.genreIds?.map { id ->
            genres?.find { finder -> finder.id == id }?.name?.let { result ->
                genreString.add(result)
            }
        }

        val model = MovieModel(
            adult = it.adult ?: false,
            backdropPath = it.backdropPath ?: "",
            genreIds = it.genreIds ?: emptyList(),
            genreString = genreString,
            id = it.id ?: 0,
            originalLanguage = it.originalLanguage ?: "",
            originalTitle = it.originalTitle ?: "",
            overview = it.overview ?: "",
            popularity = it.popularity ?: 0.0,
            posterPath = it.posterPath ?: "",
            releaseDate = it.releaseDate ?: "",
            title = it.title ?: "",
            video = it.video ?: false,
            voteAverage = it.voteAverage ?: 0.0,
            voteCount = it.voteCount ?: 0
        )
        dataModel.add(model)
    }


    return MovieDataModel(
        data = dataModel,
        page = this.page ?: 0,
        totalPages = this.totalPages ?: 0
    )
}