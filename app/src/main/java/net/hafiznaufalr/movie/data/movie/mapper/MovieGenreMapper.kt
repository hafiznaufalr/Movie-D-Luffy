package net.hafiznaufalr.movie.data.movie.mapper

import net.hafiznaufalr.movie.data.movie.model.GenreModel
import net.hafiznaufalr.movie.data.movie.response.genre.MovieGenreResponse

fun MovieGenreResponse.mapToModel(): List<GenreModel> {

    val genres = this.genres?.map {
        GenreModel(
            id = it.id ?: 0,
            name = it.name ?: ""
        )
    } ?: emptyList()

    return genres
}