package com.example.login.ui.mainScreens.settings

import androidx.lifecycle.MutableLiveData
import com.example.login.arch.BaseViewModel

class SettingsViewModel: BaseViewModel() {
    var darkModeState = MutableLiveData(false)
    var showAlertsState = MutableLiveData(false)
    var alertsVisible = MutableLiveData(false)
    var languageState: MutableLiveData<String> = MutableLiveData()

    init {
        languageState.value = "English"
    }
}