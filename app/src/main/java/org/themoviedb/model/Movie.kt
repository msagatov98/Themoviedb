package org.themoviedb.model

import java.io.Serializable
import com.google.gson.annotations.SerializedName

class Movie: Serializable{
    @SerializedName("title") val title: String = ""
    @SerializedName("budget") val budget: Int = 0
    @SerializedName("tagline") val tagline: String = ""
    @SerializedName("overview") val overview: String = ""
    @SerializedName("poster_path") val posterPath: String = ""
    @SerializedName("vote_average") val rating: Float = 0.0F
    @SerializedName("release_date") val releaseDate: String = ""
    @SerializedName("backdrop_path") val backdropPath: String = ""
    @SerializedName("original_language") val originalLanguage: String = ""
}