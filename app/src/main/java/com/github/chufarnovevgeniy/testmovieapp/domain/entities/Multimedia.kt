package com.github.chufarnovevgeniy.testmovieapp.domain.entities

import com.google.gson.annotations.SerializedName

data class Multimedia(
    @SerializedName("src") val imageSrc: String
)