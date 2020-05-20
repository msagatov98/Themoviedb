package org.themoviedb

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_movie_page.*

class MoviePageActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_page)

        this.window.statusBarColor = getColor(R.color.colorMain)


        back.setOnClickListener { finish() }
    }
}
