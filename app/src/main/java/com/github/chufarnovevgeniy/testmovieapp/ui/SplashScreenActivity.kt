package com.github.chufarnovevgeniy.testmovieapp.ui

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.postDelayed
import com.github.chufarnovevgeniy.testmovieapp.databinding.ActivitySplashScreenBinding

private const val SPLASH_TIMEOUT = 2000L

@SuppressLint("CustomSplashScreen")
class SplashScreenActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySplashScreenBinding

    private val handler = Handler(Looper.getMainLooper())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySplashScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (savedInstanceState == null) {
            handler.postDelayed(SPLASH_TIMEOUT) {
                val intent = Intent(this, MovieActivity::class.java)
                    .setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)

                startActivity(intent)
            }
        }
    }
}