package com.example.login.ui.mainScreens.settings

import androidx.lifecycle.MutableLiveData
import com.example.login.arch.BaseViewModel

class SettingsViewModel: BaseViewModel() {
    val currentLanguage: MutableLiveData<String> = MutableLiveData()

    init {
        currentLanguage.value = "English"
    }
}