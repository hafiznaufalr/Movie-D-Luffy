package net.hafiznaufalr.movie.data.movie.response


import com.google.gson.annotations.SerializedName

data class MovieGenreResponse(
    @SerializedName("genres")
    val genres: List<GenreResponse>?
)

data class GenreResponse(
    @SerializedName("id")
    val id: Int?,
    @SerializedName("name")
    val name: String?
)