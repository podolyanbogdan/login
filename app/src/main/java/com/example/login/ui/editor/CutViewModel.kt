package com.example.login.ui.editor

import androidx.lifecycle.MutableLiveData
import com.example.login.arch.BaseViewModel
import com.example.login.data.Actions
import com.example.login.data.BWTypes


class CutViewModel : BaseViewModel() {
    val actions: MutableLiveData<Actions> = MutableLiveData()
    val bwTypes: MutableLiveData<BWTypes> = MutableLiveData()

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

    fun onBwType1(){
        bwTypes.value = BWTypes.TYPES1
    }
    fun onBwType2(){
        bwTypes.value = BWTypes.TYPES2
    }
    fun onBwType3(){
        bwTypes.value = BWTypes.TYPES3
    }
    fun onBwType4(){
        bwTypes.value = BWTypes.TYPES4
    }
    fun onBwType5(){
        bwTypes.value = BWTypes.TYPES5
    }



}