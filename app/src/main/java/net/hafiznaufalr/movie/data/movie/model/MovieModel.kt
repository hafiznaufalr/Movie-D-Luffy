package net.hafiznaufalr.movie.data.movie.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class MovieModel(
    val adult: Boolean,
    val backdropPath: String,
    val genreIds: List<Int>,
    val genreString: List<String>,
    val id: Int,
    val originalLanguage: String,
    val originalTitle: String,
    val overview: String,
    val popularity: Double,
    val posterPath: String,
    val releaseDate: String,
    val title: String,
    val video: Boolean,
    val voteAverage: Double,
    val voteCount: Int
): Parcelable

@Parcelize
data class MovieDataModel(
    val data: List<MovieModel>,
    val page: Int,
    val totalPages: Int
): Parcelable