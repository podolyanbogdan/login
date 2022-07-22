package com.example.login.ui.settings

import androidx.lifecycle.MutableLiveData
import com.example.login.arch.BaseViewModel
import com.example.login.repository.MyRepository

class SettingsViewModel(private val repo: MyRepository): BaseViewModel() {
    val levelValue: MutableLiveData<Float> = MutableLiveData()
    val levelValueText: MutableLiveData<String> = MutableLiveData()
    var radioValue = MutableLiveData<Int>()

    init {
        levelValue.value = repo.getLevel().toFloat()
        levelValueText.value = repo.getLevel()
        radioValue.value = repo.getCharacterId()
    }

    //level
    fun onValueChanged(value: Float) {
        levelValue.value = value
        levelValueText.value = value.toInt().toString()
        repo.saveLevel(value)
    }
}