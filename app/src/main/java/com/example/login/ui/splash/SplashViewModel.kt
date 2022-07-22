package com.example.login.ui.splash

import androidx.lifecycle.MutableLiveData
import com.example.login.arch.BaseViewModel
import com.example.login.data.ScreensState
import com.example.login.repository.MyRepository
import kotlinx.coroutines.delay

class SplashViewModel(private val repo: MyRepository) : BaseViewModel() {
    val currentNavigation = MutableLiveData<ScreensState>()

    init {
        onLoading(true)
        launch {
            delay(2000)
            checkScreenStates()
        }
    }

    private fun checkScreenStates() {
        if (
            repo.getAgeScreenState() &&
            repo.getLevelScreenState() &&
            repo.getCharacterScreenState()
        ) {
            currentNavigation.value = ScreensState.MAP
        }

        if (
            !repo.getAgeScreenState() &&
            !repo.getLevelScreenState() &&
            !repo.getCharacterScreenState()
        ) {
            currentNavigation.value = ScreensState.AGE
        }

        if (
            repo.getAgeScreenState() &&
            !repo.getLevelScreenState() &&
            !repo.getCharacterScreenState()
        ) {
            currentNavigation.value = ScreensState.LEVEL
        }

        if (
            repo.getAgeScreenState() &&
            repo.getLevelScreenState() &&
            !repo.getCharacterScreenState()
        ) {
            currentNavigation.value = ScreensState.CHARACTER
        }
    }

}