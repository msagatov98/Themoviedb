package org.themoviedb

import com.google.gson.annotations.SerializedName

class GetMoviesResponse(
    @SerializedName("page") val page: Int,
    @SerializedName("total_pages") val pages: Int,
    @SerializedName("results") val movies: List<Movie>
)