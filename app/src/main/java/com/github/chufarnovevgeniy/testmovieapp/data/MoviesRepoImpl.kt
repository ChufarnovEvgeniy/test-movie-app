package com.github.chufarnovevgeniy.testmovieapp.data

import androidx.lifecycle.LiveData
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.liveData
import com.github.chufarnovevgeniy.testmovieapp.domain.MoviesRepo
import com.github.chufarnovevgeniy.testmovieapp.domain.entities.MovieEntity
import com.github.chufarnovevgeniy.testmovieapp.utils.MOVIES_PAGE_SIZE
import kotlinx.coroutines.flow.Flow

class MoviesRepoImpl(
    private val moviesApi: MoviesApi
) : MoviesRepo {

    override fun getMoviesResultStream(): LiveData<PagingData<MovieEntity>> {
        return Pager(
            PagingConfig(
                pageSize = MOVIES_PAGE_SIZE,
                enablePlaceholders = false
            ),
            pagingSourceFactory = { MoviesPagingService(moviesApi) }
        ).liveData
    }
}