package org.themoviedb.retrofit

import com.google.gson.annotations.SerializedName
import org.themoviedb.model.Movie

class GetMoviesResponse(
    @SerializedName("page") val page: Int,
    @SerializedName("total_pages") val pages: Int,
    @SerializedName("results") val movies: List<Movie>
)