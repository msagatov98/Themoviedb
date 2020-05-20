package org.themoviedb

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*

class MovieListActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        this.window.statusBarColor = getColor(R.color.colorMain)

        val divider = DividerItemDecoration(this, DividerItemDecoration.VERTICAL)

        movie_list.addItemDecoration(divider)
        movie_list.layoutManager = LinearLayoutManager(this)
        movie_list.adapter = AdapterMovie(this)
    }
}
