package com.github.chufarnovevgeniy.testmovieapp

import android.app.Application
import androidx.fragment.app.Fragment
import com.github.chufarnovevgeniy.testmovieapp.data.MoviesApi
import com.github.chufarnovevgeniy.testmovieapp.data.MoviesRepoImpl
import com.github.chufarnovevgeniy.testmovieapp.domain.MoviesRepo
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class App : Application() {
    private val moviesApi by lazy {
        Retrofit.Builder()
            .baseUrl(BuildConfig.MOVIE_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(MoviesApi::class.java)
    }

    val moviesRepo: MoviesRepo by lazy { MoviesRepoImpl(moviesApi) }
}

val Fragment.app: App
    get() = requireActivity().application as App