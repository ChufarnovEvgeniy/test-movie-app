package com.github.chufarnovevgeniy.testmovieapp.data

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.github.chufarnovevgeniy.testmovieapp.domain.entities.MovieEntity
import com.github.chufarnovevgeniy.testmovieapp.utils.MOVIES_PAGE_SIZE
import retrofit2.HttpException
import java.io.IOException

const val MOVIES_STARTING_PAGE_INDEX = 0

class MoviesPagingService(
    private val moviesApi: MoviesApi,
) : PagingSource<Int, MovieEntity>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, MovieEntity> {

        val position = params.key ?: MOVIES_STARTING_PAGE_INDEX

        return try {
            val movies = moviesApi.getMovies(position).movies

            LoadResult.Page(
                movies,
                getPreviousKey(position),
                getNextKey(position, movies)
            )

        } catch (exception: IOException) {
            return LoadResult.Error(exception)
        } catch (exception: HttpException) {
            return LoadResult.Error(exception)
        }
    }

    private fun getPreviousKey(position: Int): Int? = if (position == MOVIES_STARTING_PAGE_INDEX) {
        null
    } else {
        position - MOVIES_PAGE_SIZE
    }

    private fun getNextKey(position: Int, movies: List<MovieEntity>): Int? = if (movies.isEmpty()) {
        null
    } else {
        position + MOVIES_PAGE_SIZE
    }

    override fun getRefreshKey(state: PagingState<Int, MovieEntity>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(MOVIES_PAGE_SIZE)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(MOVIES_PAGE_SIZE)
        }
    }
}