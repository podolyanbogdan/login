package com.example.login.ui.dialogFragment

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.login.arch.BaseViewModel

class DialogViewModel: ViewModel() {
    val wifiName: MutableLiveData<String> = MutableLiveData()
    val wifiDescription: MutableLiveData<String> = MutableLiveData()
    val btnDissmiss: MutableLiveData<Boolean> = MutableLiveData()

    fun onClick(){
        btnDissmiss.value = true
    }
}