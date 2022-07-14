package com.example.login.ui.dialogFragment

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class TagDialogViewModel : ViewModel() {
    val cancel: MutableLiveData<Boolean> = MutableLiveData()
    val save: MutableLiveData<Boolean> = MutableLiveData()

    fun cancelClick() {
        cancel.value = true
    }

    fun saveClick() {
        save.value = true
    }


}