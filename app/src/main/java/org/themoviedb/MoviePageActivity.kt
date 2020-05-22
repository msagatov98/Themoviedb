package org.themoviedb

import android.content.Context
import android.net.ConnectivityManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import kotlinx.android.synthetic.main.activity_movie_page.*
import org.themovied.isNetworkAvailable
import org.themovied.showToast

class MoviePageActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_page)
        this.window.statusBarColor = getColor(R.color.colorMain)
        back.setOnClickListener { finish() }

        swipeContainer.setOnRefreshListener {
            showMovieInfo()
            swipeContainer.isRefreshing = false
        }

        showMovieInfo()
    }

    private fun showMovieInfo() {
        if (isNetworkAvailable()) {
            val movie = intent.getSerializableExtra("movie") as Movie

            movie_title.text = movie.title
            movie_rating.text = movie.rating.toString()
            movie_budget.text = movie.budget.toString()
            movie_tagline.text = movie.tagline
            movie_overview.text = movie.overview
            movie_release_date.text = movie.releaseDate

            Glide
                .with(this)
                .load("https://image.tmdb.org/t/p/w342${movie.backdropPath}")
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(movie_backdrop)
        } else {
            showToast("No network")
        }
    }
}