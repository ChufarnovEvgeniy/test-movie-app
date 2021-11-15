package com.github.chufarnovevgeniy.testmovieapp.domain.entities

import com.google.gson.annotations.SerializedName

data class MovieEntity(
    @SerializedName("display_title") val title: String,
    @SerializedName("summary_short") val description: String,
    @SerializedName("multimedia") val multimedia: Multimedia?
)