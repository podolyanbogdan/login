package com.example.login.ui.level

import androidx.lifecycle.MutableLiveData
import com.example.login.arch.BaseViewModel
import com.example.login.constants.Constants.ZERO
import com.example.login.repository.MyRepository

class LevelViewModel : BaseViewModel() {
    val prevClick: MutableLiveData<Boolean> = MutableLiveData()
    val nextClick: MutableLiveData<Boolean> = MutableLiveData()
    val levelValue: MutableLiveData<String> = MutableLiveData()

    init {
        levelValue.value = ZERO
    }

    fun onValueChanged(value: Float) {
        levelValue.value = value.toInt().toString()
        MyRepository.getLevel(value.toInt())
    }

    fun prevClicked() {
        prevClick.value = true
    }

    fun nextClicked() {
        nextClick.value = true
    }
}