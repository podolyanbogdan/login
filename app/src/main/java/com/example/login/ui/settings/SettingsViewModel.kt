package com.example.login.ui.settings

import androidx.lifecycle.MutableLiveData
import com.example.login.R
import com.example.login.arch.BaseViewModel
import com.example.login.music.Music

class SettingsViewModel : BaseViewModel() {
    val isPlayAgain: MutableLiveData<Boolean> = MutableLiveData()
    val isContinue: MutableLiveData<Boolean> = MutableLiveData()
    val isExit: MutableLiveData<Boolean> = MutableLiveData()
    val turnMusic: MutableLiveData<Boolean> = MutableLiveData()
    val offMusic: MutableLiveData<Boolean> = MutableLiveData()
    private var isMusicPlay = true

    fun playAgain() {
        isPlayAgain.value = true
    }
    fun continueGame() {
        isContinue.value = true
    }
    fun exitGame() {
        isExit.value = true
    }

    fun onMusic(){
        if(!isMusicPlay){
            turnMusic.value = true
            isMusicPlay = true
        }
    }
    fun offMusic(){
        if(isMusicPlay){
            offMusic.value = true
            isMusicPlay = false
        }
    }
}