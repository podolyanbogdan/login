package com.example.login.presentation

import android.animation.ObjectAnimator
import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.AnimationDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.login.R
import com.example.login.databinding.ActivityCustomSplashBinding
import kotlinx.coroutines.*

@SuppressLint("CustomSplashScreen")
class CustomSplashActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCustomSplashBinding
    private lateinit var animationDrawable: AnimationDrawable
    private val activityScope = CoroutineScope(Dispatchers.Main)
    private var splashScreenTime: Long = 5000
    private var zero: Long = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCustomSplashBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()
        initProgressBar()
        initNinja()
        launchAct()
    }

    private fun initProgressBar(){
        binding.progressBar.progressDrawable.setColorFilter(
            Color.RED, android.graphics.PorterDuff.Mode.SRC_IN);
        binding.progressBar.max = 1000
        val currentProgress = 1000
        ObjectAnimator.ofInt(binding.progressBar, "progress", currentProgress)
            .setDuration(splashScreenTime)
            .start()
    }

    private fun initNinja(){
        binding.imgNinja.setBackgroundResource(R.drawable.ninja_animation_running)
        animationDrawable = binding.imgNinja.background as AnimationDrawable
        animationDrawable.start()
    }

    private fun launchAct(){
        activityScope.launch{
            while(splashScreenTime != zero)
            {
                splashScreenTime -= 1000
                delay(1000)
            }
            if(splashScreenTime == zero)
            {
                var intent = Intent(this@CustomSplashActivity, MainActivity::class.java)
                startActivity(intent)
                finish()
            }
        }
    }

    override fun onPause() {
        super.onPause()
        if(splashScreenTime == zero)
        {
            launchAct()
            activityScope.cancel()
        }
    }
    override fun onRestart() {
        super.onRestart()
        if(splashScreenTime == zero)
        {
            launchAct()
            activityScope.cancel()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        activityScope.cancel()
    }
}