package net.hafiznaufalr.movie.data.movie.mapper

import net.hafiznaufalr.movie.data.movie.response.MovieTrailerResponse

fun List<MovieTrailerResponse>.mapToModel(): String {
    var movieTrailer = ""
    val fromYoutube = this.filter {
        it.site?.contains("youtube") ?: false
    }.findLast {
        it.name?.contains("official trailer", true) ?: false
    }

    movieTrailer = if (fromYoutube != null) {
        fromYoutube.key ?: ""
    } else {
        this.last().key ?: ""
    }
    return movieTrailer
}