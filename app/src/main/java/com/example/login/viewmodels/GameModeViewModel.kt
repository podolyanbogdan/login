package com.example.login.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class GameModeViewModel: ViewModel() {
    var playerPlayerMode = MutableLiveData<Boolean>()
    var playerComputerMode = MutableLiveData<Boolean>()


    fun clickPlayerPlayer(){
        playerPlayerMode.value = true
    }
    fun clickPlayerComputer(){
        playerComputerMode.value = true
    }
}