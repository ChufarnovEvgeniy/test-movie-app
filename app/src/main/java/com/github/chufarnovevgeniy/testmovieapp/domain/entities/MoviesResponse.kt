package com.github.chufarnovevgeniy.testmovieapp.domain.entities

import com.google.gson.annotations.SerializedName

data class MoviesResponse(
    @SerializedName("has_more") val hasMore: Boolean,
    @SerializedName("results") val movies: List<MovieEntity>
)