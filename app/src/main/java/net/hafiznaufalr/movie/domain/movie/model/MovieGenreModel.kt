package net.hafiznaufalr.movie.domain.movie.model

import com.google.gson.annotations.SerializedName

data class MovieGenreModel(
    @SerializedName("genres")
    val genres: List<GenreModel>
)

data class GenreModel(
    val id: Int,
    val name: String
)