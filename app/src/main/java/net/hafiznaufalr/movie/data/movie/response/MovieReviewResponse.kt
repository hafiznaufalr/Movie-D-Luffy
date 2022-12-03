package net.hafiznaufalr.movie.data.movie.response


import com.google.gson.annotations.SerializedName

data class MovieReviewResponse(
    @SerializedName("author")
    val author: String?,
    @SerializedName("author_details")
    val authorDetails: MovieReviewAuthorResponse?,
    @SerializedName("content")
    val content: String?,
    @SerializedName("created_at")
    val createdAt: String?,
    @SerializedName("id")
    val id: String?,
    @SerializedName("updated_at")
    val updatedAt: String?,
    @SerializedName("url")
    val url: String?
)

data class MovieReviewAuthorResponse(
    @SerializedName("avatar_path")
    val avatarPath: String?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("rating")
    val rating: Double?,
    @SerializedName("username")
    val username: String?
)