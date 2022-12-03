package net.hafiznaufalr.movie.data.movie.remote

import net.hafiznaufalr.movie.BuildConfig
import net.hafiznaufalr.movie.data.movie.response.BaseResponse
import net.hafiznaufalr.movie.data.movie.response.MovieGenreResponse
import net.hafiznaufalr.movie.data.movie.response.MovieResponse
import net.hafiznaufalr.movie.data.movie.response.MovieReviewResponse
import net.hafiznaufalr.movie.data.movie.response.MovieTrailerResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieApi {
    @GET("movie/now_playing")
    suspend fun getNowPlaying(
        @Query("api_key") apikey: String = BuildConfig.API_KEY
    ): BaseResponse<List<MovieResponse>>

    @GET("movie/popular")
    suspend fun getPopular(
        @Query("api_key") apikey: String = BuildConfig.API_KEY,
        @Query("page") page: Int
    ): BaseResponse<List<MovieResponse>>

    @GET("genre/movie/list")
    suspend fun getMovieGenre(
        @Query("api_key") apikey: String = BuildConfig.API_KEY
    ): MovieGenreResponse

    @GET("movie/{movie_id}/reviews")
    suspend fun getMovieReviews(
        @Path("movie_id") movieId: Int,
        @Query("api_key") apikey: String = BuildConfig.API_KEY,
    ): BaseResponse<List<MovieReviewResponse>>

    @GET("movie/{movie_id}/videos")
    suspend fun getMovieTrailer(
        @Path("movie_id") movieId: Int,
        @Query("api_key") apikey: String = BuildConfig.API_KEY,
    ): BaseResponse<List<MovieTrailerResponse>>
}