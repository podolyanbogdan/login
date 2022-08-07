package com.example.login.ui.home

import androidx.lifecycle.MutableLiveData
import com.example.login.arch.BaseViewModel
import com.example.login.data.Direction

class HomeViewModel: BaseViewModel() {
    val direction: MutableLiveData<Direction> = MutableLiveData()

    fun toEditPhoto(){
        direction.value = Direction.EDIT
    }

    fun openSettings(){
        direction.value = Direction.SETTINGS
    }
}