package com.example.login.music

import android.content.Context
import android.media.MediaPlayer
import com.example.login.R

object Music{
    private lateinit var ring: MediaPlayer
    fun startMusic(context: Context){
        ring = MediaPlayer.create(context, R.raw.the_question_game_illurock)
        ring.start()
        ring.isLooping = true
    }
    fun stopMusic(){
        ring.stop()
    }
}