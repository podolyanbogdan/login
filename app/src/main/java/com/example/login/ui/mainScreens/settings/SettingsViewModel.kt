package com.example.login.ui.mainScreens.settings

import androidx.lifecycle.MutableLiveData
import com.example.login.arch.BaseViewModel
import com.example.login.data.repository.ForecastStorage

class SettingsViewModel(
   private val repo: ForecastStorage
): BaseViewModel() {
    var darkModeState = MutableLiveData(false)
    var showAlertsState = MutableLiveData(false)
    var alertsVisible = MutableLiveData(false)
}