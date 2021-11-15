package com.github.chufarnovevgeniy.testmovieapp.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.github.chufarnovevgeniy.testmovieapp.R
import com.github.chufarnovevgeniy.testmovieapp.ui.movies.MoviesFragment

class MovieActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .add(R.id.container_layout, MoviesFragment())
                .commit()
        }
    }
}