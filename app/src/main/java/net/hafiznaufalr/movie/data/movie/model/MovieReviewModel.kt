package net.hafiznaufalr.movie.data.movie.model


data class MovieReviewModel(
    val author: String,
    val authorDetails: MovieReviewAuthorModel,
    val content: String,
    val createdAt: String,
    val id: String,
    val updatedAt: String?,
    val url: String?
)

data class MovieReviewAuthorModel(
    val avatarPath: String,
    val name: String,
    val rating: Double,
    val username: String
)