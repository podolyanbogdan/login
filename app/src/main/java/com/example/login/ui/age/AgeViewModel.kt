package com.example.login.ui.age

import androidx.lifecycle.MutableLiveData
import com.example.login.arch.BaseViewModel

class AgeViewModel : BaseViewModel() {
    val btnClicked: MutableLiveData<Boolean> = MutableLiveData()
    val switchState: MutableLiveData<Boolean> = MutableLiveData()

    fun btnClicked() {
        btnClicked.value = true
    }
    fun onCheckedChanged(checked: Boolean){
        switchState.value = checked
    }
}