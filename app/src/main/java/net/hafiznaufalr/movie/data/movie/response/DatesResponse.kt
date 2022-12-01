package net.hafiznaufalr.movie.data.movie.response


import com.google.gson.annotations.SerializedName

data class DatesResponse(
    @SerializedName("maximum")
    val maximum: String?,
    @SerializedName("minimum")
    val minimum: String?
)