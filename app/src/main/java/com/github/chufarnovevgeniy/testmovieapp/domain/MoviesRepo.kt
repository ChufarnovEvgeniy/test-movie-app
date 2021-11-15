package com.github.chufarnovevgeniy.testmovieapp.domain

import androidx.lifecycle.LiveData
import androidx.paging.PagingData
import com.github.chufarnovevgeniy.testmovieapp.domain.entities.MovieEntity
import kotlinx.coroutines.flow.Flow

interface MoviesRepo {
    fun getMoviesResultStream(): LiveData<PagingData<MovieEntity>>
}