package com.example.login.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SplashViewModel: ViewModel() {
    var trigger = MutableLiveData<Boolean>()

    fun clickBtn(){
        trigger.value = true
    }

}