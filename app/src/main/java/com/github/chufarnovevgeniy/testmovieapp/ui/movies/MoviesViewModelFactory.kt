package com.github.chufarnovevgeniy.testmovieapp.ui.movies

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.github.chufarnovevgeniy.testmovieapp.domain.MoviesRepo

class MoviesViewModelFactory(private val moviesRepo: MoviesRepo) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return modelClass.getConstructor(MoviesRepo::class.java)
            .newInstance(moviesRepo)
    }
}