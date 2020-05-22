package org.themoviedb.retrofit

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface Api {

    @GET("movie/popular")
    fun getPopularMovies(
        @Query("api_key") apiKey: String = "2c5606df26248bb4758c3287afaaad1e",
        @Query("page") page: Int
    ): Call<GetMoviesResponse>
}