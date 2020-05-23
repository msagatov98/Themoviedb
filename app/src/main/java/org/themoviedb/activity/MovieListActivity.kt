package org.themoviedb.activity

import android.content.res.Configuration
import org.themoviedb.R
import android.util.Log
import android.os.Bundle
import android.widget.Toast
import org.themoviedb.model.Movie
import org.themoviedb.util.showToast
import org.themoviedb.adapter.AdapterMovie
import org.themoviedb.util.isNetworkAvailable
import org.themoviedb.retrofit.MoviesRepository
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.*
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager

class MovieListActivity : AppCompatActivity() {

    private lateinit var popularMoviesAdapter: AdapterMovie
    private lateinit var popularMoviesLayoutGrdMgr: GridLayoutManager
    private lateinit var popularMoviesLayoutLnrMgr: LinearLayoutManager

    private lateinit var dividerVertical : DividerItemDecoration
    private lateinit var dividerHorizontal : DividerItemDecoration


    private var popularMoviesPage = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        this.window.statusBarColor = getColor(R.color.colorMain)

        dividerVertical = DividerItemDecoration(this, DividerItemDecoration.VERTICAL)
        dividerHorizontal = DividerItemDecoration(this, DividerItemDecoration.HORIZONTAL)

        movie_list.addItemDecoration(dividerVertical)
        if (resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            movie_list.addItemDecoration(dividerHorizontal)
        }

        swipeContainerMovieList.setOnRefreshListener {
            showMovies()
            swipeContainerMovieList.isRefreshing = false
        }

        showMovies()
    }

    private fun showMovies() {

            if (resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE) {
                popularMoviesLayoutGrdMgr = GridLayoutManager(this, 2)
                movie_list.layoutManager = popularMoviesLayoutGrdMgr
                popularMoviesAdapter = AdapterMovie(mutableListOf())
                movie_list.adapter = popularMoviesAdapter
                getPopularMovies()
            } else {
                popularMoviesLayoutLnrMgr = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
                movie_list.layoutManager = popularMoviesLayoutLnrMgr
                popularMoviesAdapter = AdapterMovie(mutableListOf())
                movie_list.adapter = popularMoviesAdapter
                getPopularMovies()
            }

        if (!isNetworkAvailable()) {
            showToast("No network")
        }
    }

    private fun getPopularMovies() {
        MoviesRepository.init(this)
        MoviesRepository.getMovies(
            popularMoviesPage,
            ::onPopularMoviesFetched,
            ::onError
        )
    }

    private fun attachPopularMoviesOnScrollListener() {
        movie_list.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {

                val totalItemCount: Int
                val visibleItemCount: Int
                val firstVisibleItem: Int

                if (resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE) {
                    popularMoviesPage = 1
                    totalItemCount = popularMoviesLayoutGrdMgr.itemCount
                    visibleItemCount = popularMoviesLayoutGrdMgr.childCount
                    firstVisibleItem = popularMoviesLayoutGrdMgr.findFirstVisibleItemPosition()
                } else {
                    popularMoviesPage = 1
                    totalItemCount = popularMoviesLayoutLnrMgr.itemCount
                    visibleItemCount = popularMoviesLayoutLnrMgr.childCount
                    firstVisibleItem = popularMoviesLayoutLnrMgr.findFirstVisibleItemPosition()
                }

                Log.e("TAG", "$totalItemCount\n$visibleItemCount\n$firstVisibleItem")

                if (firstVisibleItem + visibleItemCount >= totalItemCount - 1) {
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