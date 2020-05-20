package org.themoviedb

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.view_holder_movie.view.*
import kotlin.coroutines.coroutineContext

class AdapterMovie(var context: Context): RecyclerView.Adapter<AdapterMovie.MovieViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.view_holder_movie, parent, false)
        return MovieViewHolder(view)
    }

    override fun getItemCount(): Int {
        return 10
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.itemView.movie_date.text = "Date"
        holder.itemView.movie_title.text = "Title"
        holder.itemView.movie_image.setImageResource(R.drawable.tmdb_logo)

        holder.itemView.movie_container.setOnClickListener {
            val intent = Intent(context, MoviePageActivity::class.java)
            context.startActivity(intent)
        }
    }

    class MovieViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)
}