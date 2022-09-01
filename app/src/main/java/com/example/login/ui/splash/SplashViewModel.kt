package com.example.login.ui.splash

import androidx.lifecycle.MutableLiveData
import com.example.login.arch.BaseViewModel
import com.example.login.arch.lifecycle.SingleLiveEvent
import com.example.login.data.enumss.IntroduceScreenState
import com.example.login.data.repository.PrefsRepository
import kotlinx.coroutines.delay

class SplashViewModel(
    private val repo: PrefsRepository
) : BaseViewModel() {

    val screenState: MutableLiveData<IntroduceScreenState> = MutableLiveData()

    init {
        onLoading(true)
        launch {
            delay(2000)
            if(repo.checkLocationState()){
                screenState.value =  IntroduceScreenState.LOCATION_ACCEPT
            }
            when(repo.checkScreenState()){
                true -> screenState.value = IntroduceScreenState.WATCHED
                false -> screenState.value = IntroduceScreenState.UNWATCHED
            }
        }
    }
}