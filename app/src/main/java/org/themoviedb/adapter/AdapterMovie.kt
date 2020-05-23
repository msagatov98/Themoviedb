package org.themoviedb.adapter

import org.themoviedb.R
import android.view.View
import android.content.Intent
import android.view.ViewGroup
import android.widget.TextView
import android.widget.ImageView
import com.bumptech.glide.Glide
import org.themoviedb.model.Movie
import android.view.LayoutInflater
import androidx.recyclerview.widget.RecyclerView
import org.themoviedb.activity.MoviePageActivity
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import kotlinx.android.synthetic.main.view_holder_movie.view.*

class AdapterMovie(
    private var movies: MutableList<Movie>
): RecyclerView.Adapter<AdapterMovie.MovieViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.view_holder_movie, parent, false)
        return MovieViewHolder(view)
    }

    override fun getItemCount(): Int {
        return movies.size / 2
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bind(movies[position])
    }

    fun appendMovies(movies: List<Movie>) {
        this.movies.addAll(movies)
        notifyItemRangeInserted(this.movies.size, movies.size - 1)
    }

    class MovieViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

        private val movieTitle: TextView = itemView.movie_title
        private val movieRating: TextView = itemView.movie_rating
        private val movieReleaseDate: TextView = itemView.movie_date
        private val moviePosterPath: ImageView = itemView.movie_image


        fun bind(movie: Movie) {
            movieTitle.text = movie.title
            movieRating.text = movie.rating.toString()
            movieReleaseDate.text = movie.releaseDate
            Glide.with(itemView).load("https://image.tmdb.org/t/p/w342${movie.posterPath}").transform(CenterCrop()).into(moviePosterPath)


            itemView.movie_container.setOnClickListener {

                val intent = Intent(itemView.context, MoviePageActivity::class.java)
                intent.putExtra("movie", movie)

                itemView.context.startActivity(intent)
            }
        }
    }
}