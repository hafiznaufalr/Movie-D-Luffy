package net.hafiznaufalr.movie.data.movie.response


import com.google.gson.annotations.SerializedName
import net.hafiznaufalr.movie.data.movie.response.movie.DatesResponse

data class BaseResponse<T>(
    @SerializedName("dates")
    val dates: DatesResponse?,
    @SerializedName("page")
    val page: Int?,
    @SerializedName("results")
    val results: T?,
    @SerializedName("total_pages")
    val totalPages: Int?,
    @SerializedName("total_results")
    val totalResults: Int?
)