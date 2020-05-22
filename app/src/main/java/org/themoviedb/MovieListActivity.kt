package org.themoviedb

import android.content.Context
import android.net.ConnectivityManager
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.*
import org.themovied.isNetworkAvailable
import org.themovied.showToast


class MovieListActivity : AppCompatActivity() {

    private lateinit var popularMoviesAdapter: AdapterMovie
    private lateinit var popularMoviesLayoutMgr: LinearLayoutManager

    private var popularMoviesPage = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        this.window.statusBarColor = getColor(R.color.colorMain)
        val divider = DividerItemDecoration(this, DividerItemDecoration.VERTICAL)
        movie_list.addItemDecoration(divider)

        swipeContainer.setOnRefreshListener {
            showMovies()
            swipeContainer.isRefreshing = false
        }

        showMovies()
    }

    private fun showMovies() {

        if (isNetworkAvailable()) {
            popularMoviesLayoutMgr = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
            movie_list.layoutManager = popularMoviesLayoutMgr
            popularMoviesAdapter = AdapterMovie(mutableListOf())
            movie_list.adapter = popularMoviesAdapter

            getPopularMovies()
        } else {
            showToast("No network")
        }
    }

    private fun getPopularMovies() {
        MoviesRepository.getMovies(
            popularMoviesPage,
            ::onPopularMoviesFetched,
            ::onError
        )
    }

    private fun attachPopularMoviesOnScrollListener() {
        movie_list.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                val totalItemCount = popularMoviesLayoutMgr.itemCount
                val visibleItemCount = popularMoviesLayoutMgr.childCount
                val firstVisibleItem = popularMoviesLayoutMgr.findFirstVisibleItemPosition()

                Log.e("TAG", "$totalItemCount\n$visibleItemCount\n$firstVisibleItem")

                if (firstVisibleItem + visibleItemCount >= totalItemCount-1) {
                    movie_list.removeOnScrollListener(this)
                    popularMoviesPage++
                    getPopularMovies()
                }
            }
        })
    }

    private fun onPopularMoviesFetched(movies: List<Movie>) {
        popularMoviesAdapter.appendMovies(movies)
        attachPopularMoviesOnScrollListener()
    }

    private fun onError() {
        Toast.makeText(this, "Error fetch movies", Toast.LENGTH_SHORT).show()

    }
}
