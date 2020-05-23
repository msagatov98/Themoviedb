package org.themoviedb.retrofit

import android.content.Context
import okhttp3.Cache
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import org.themoviedb.model.Movie
import retrofit2.converter.gson.GsonConverterFactory

object MoviesRepository {

    private lateinit var api: Api

    fun init(context: Context) {
        val cacheSize = (5 * 1024 * 1024).toLong()

        val cache = Cache(context.cacheDir, cacheSize)

        val okHttpClient = OkHttpClient.Builder().cache(cache).build()

        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.themoviedb.org/3/")
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        api = retrofit.create(Api::class.java)
    }

    fun getMovies(page: Int = 1,
                  onSuccess: (movies: List<Movie>) -> Unit,
                  onError: () -> Unit
    ) {
        api.getPopularMovies(page = page).enqueue(object : Callback<GetMoviesResponse> {
                override fun onResponse(call: Call<GetMoviesResponse>, response: Response<GetMoviesResponse>) {
                    if (response.isSuccessful) {
                        val responseBody = response.body()

                        if (responseBody != null) {
                            onSuccess.invoke(responseBody.movies)
                        } else {
                            onError.invoke()
                        }
                    }
                }

                override fun onFailure(call: Call<GetMoviesResponse>, t: Throwable) {
                    onError.invoke()
                }
            })
    }
}