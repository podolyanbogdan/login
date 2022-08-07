package com.example.login.ui.editor

import androidx.lifecycle.MutableLiveData
import com.example.login.arch.BaseViewModel
import com.example.login.data.Actions

class EditorViewModel: BaseViewModel() {
    val actions: MutableLiveData<Actions> = MutableLiveData()

    fun toHome(){
        actions.value = Actions.HOME
    }
    fun saveImage(){
        actions.value = Actions.SAVE
    }
    fun cropImage(){
        actions.value = Actions.CROP
    }
}