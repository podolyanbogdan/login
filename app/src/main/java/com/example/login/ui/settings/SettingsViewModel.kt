package com.example.login.ui.settings

import androidx.lifecycle.MutableLiveData
import com.example.login.arch.BaseViewModel
import com.example.login.constants.Constants.CHARACTER_ID_KEY
import com.example.login.constants.Constants.LEVEL_KEY
import com.example.login.repository.PreferenceStorage

class SettingsViewModel(private val prefs: PreferenceStorage): BaseViewModel() {
    val levelValue: MutableLiveData<Float> = MutableLiveData()
    var radioValue = MutableLiveData<Int>()

    init {
        levelValue.value = prefs.getLevel(LEVEL_KEY)
        radioValue.value = prefs.getCharacterId(CHARACTER_ID_KEY)
    }

    fun onValueChanged(value: Float) {
        levelValue.value = value
        prefs.saveLevel(LEVEL_KEY, value)
    }
}