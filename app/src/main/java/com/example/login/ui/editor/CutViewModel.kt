package com.example.login.ui.editor

import androidx.lifecycle.MutableLiveData
import com.example.login.arch.BaseViewModel
import com.example.login.data.Actions

class CutViewModel : BaseViewModel() {
    val actions: MutableLiveData<Actions> = MutableLiveData()

    fun toHome() {
        actions.value = Actions.HOME
    }

    fun saveImage() {
        actions.value = Actions.SAVE_PHOTO
    }

    fun saveChanges() {
        actions.value = Actions.SAVE_CHANGES
    }

    fun resetImg() {
        actions.value = Actions.RESET
    }

    fun undoChanges() {
        actions.value = Actions.UNDO
    }
}