package com.example.login.ui.menu

import androidx.lifecycle.MutableLiveData
import com.example.login.arch.BaseViewModel

class MenuViewModel: BaseViewModel() {
    val play: MutableLiveData<Boolean> = MutableLiveData()
    val exit: MutableLiveData<Boolean> = MutableLiveData()

    fun play(){
        play.value = true
    }
    fun exit(){
        exit.value = true
    }
}