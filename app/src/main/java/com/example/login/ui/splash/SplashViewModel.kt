package com.example.login.ui.splash

import com.example.login.arch.BaseViewModel
import com.example.login.arch.lifecycle.SingleLiveEvent
import kotlinx.coroutines.delay

class SplashViewModel(

) : BaseViewModel() {

    val initEvent = SingleLiveEvent<Boolean>()

    init {
        onLoading(true)
        launch {
            delay(5000)
            initEvent.postValue(true)
        }
    }
}