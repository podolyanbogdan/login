package com.example.login.ui.map

import androidx.lifecycle.MutableLiveData
import com.example.login.arch.BaseViewModel
import com.example.login.repository.MyRepository

class MapViewModel: BaseViewModel() {
    val positionClicked: MutableLiveData<Boolean> = MutableLiveData()

    init {
        positionClicked.value = false
    }

    fun exportPosition(){
        positionClicked.value = true
    }
}