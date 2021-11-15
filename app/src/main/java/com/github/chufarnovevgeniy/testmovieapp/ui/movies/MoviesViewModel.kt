package com.github.chufarnovevgeniy.testmovieapp.ui.movies

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.github.chufarnovevgeniy.testmovieapp.domain.MoviesRepo
import com.github.chufarnovevgeniy.testmovieapp.domain.entities.MovieEntity

class MoviesViewModel(
    moviesRepo: MoviesRepo
) : ViewModel() {
    val movies: LiveData<PagingData<MovieEntity>> = moviesRepo.getMoviesResultStream()
        .cachedIn(viewModelScope)

    private val _retryEvent = MutableLiveData<Boolean?>()
    val retryEvent: LiveData<Boolean?> = _retryEvent

    fun onRetryClicked() {
        _retryEvent.value = true
    }

    fun onRetryFinished() {
        _retryEvent.value = null
    }
}