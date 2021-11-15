package com.github.chufarnovevgeniy.testmovieapp.data

import com.github.chufarnovevgeniy.testmovieapp.BuildConfig
import com.github.chufarnovevgeniy.testmovieapp.domain.entities.MoviesResponse
import retrofit2.http.GET
import retrofit2.http.Query

private const val ORDER = "by-publication-date"

interface MoviesApi {
    @GET("svc/movies/v2/reviews/all.json")
    suspend fun getMovies(
        @Query("offset") offset: Int,
        @Query("order") order: String = ORDER,
        @Query("api-key") apiKey: String = BuildConfig.MOVIE_API_KEY
    ): MoviesResponse
}