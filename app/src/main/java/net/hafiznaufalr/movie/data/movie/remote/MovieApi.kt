package net.hafiznaufalr.movie.data.movie.remote

import net.hafiznaufalr.movie.BuildConfig
import net.hafiznaufalr.movie.data.movie.response.BaseResponse
import net.hafiznaufalr.movie.data.movie.response.MovieResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieApi {
    @GET("now_playing")
    suspend fun getNowPlaying(
        @Query("api_key") apikey: String = BuildConfig.API_KEY
    ): BaseResponse<List<MovieResponse>>
}