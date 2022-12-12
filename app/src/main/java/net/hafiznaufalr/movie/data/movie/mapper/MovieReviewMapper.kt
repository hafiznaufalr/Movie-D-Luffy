package net.hafiznaufalr.movie.data.movie.mapper

import net.hafiznaufalr.movie.domain.movie.model.MovieReviewAuthorModel
import net.hafiznaufalr.movie.domain.movie.model.MovieReviewModel
import net.hafiznaufalr.movie.data.movie.response.MovieReviewResponse

fun List<MovieReviewResponse>.mapToModel(): List<MovieReviewModel> {
    return this.map {

        val authorDetails = MovieReviewAuthorModel(
            avatarPath = it.authorDetails?.avatarPath ?: "",
            name = it.authorDetails?.name ?: "",
            rating = it.authorDetails?.rating ?: 0.0,
            username = it.authorDetails?.username ?: ""
        )

        MovieReviewModel(
            author = it.author ?: "",
            authorDetails = authorDetails,
            content = it.content ?: "",
            createdAt = it.createdAt ?: "",
            id = it.id ?: "",
            updatedAt = it.updatedAt ?: "",
            url = it.url ?: ""
        )
    }
}