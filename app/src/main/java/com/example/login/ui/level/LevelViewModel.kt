package com.example.login.ui.level

import androidx.lifecycle.MutableLiveData
import com.example.login.arch.BaseViewModel
import com.example.login.constants.Constants.DEFAULT
import com.example.login.repository.MyRepository

class LevelViewModel(private val repo: MyRepository) : BaseViewModel() {
    val goPrevScreen: MutableLiveData<Boolean> = MutableLiveData()
    val goNextClick: MutableLiveData<Boolean> = MutableLiveData()
    val levelValueText: MutableLiveData<String> = MutableLiveData()

    init {
        repo.saveLevel(1f)
        levelValueText.value = DEFAULT
    }

    fun onValueChanged(value: Float) {
        levelValueText.value = value.toInt().toString()
        repo.saveLevel(value)
    }

    fun prevClicked() {
        goPrevScreen.value = true
    }

    fun nextClicked() {
        repo.saveLevelScreenState(true)
        goNextClick.value = true
    }
}