package com.example.login.ui.alert

import androidx.lifecycle.MutableLiveData
import com.example.login.arch.BaseViewModel
import com.example.login.data.enums.SettingsChoice

class SettingsDialogViewModel : BaseViewModel() {
    val permissionChoice: MutableLiveData<SettingsChoice> = MutableLiveData()

    fun onCancel() {
        permissionChoice.value = SettingsChoice.CANCEL
    }

    fun onGoTo() {
        permissionChoice.value = SettingsChoice.GO_TO
    }
}