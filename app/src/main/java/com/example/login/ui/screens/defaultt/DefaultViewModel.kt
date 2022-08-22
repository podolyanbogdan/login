package com.example.login.ui.screens.defaultt

import androidx.lifecycle.MutableLiveData
import com.example.login.arch.BaseViewModel
import com.example.login.arch.lifecycle.SingleLiveEvent
import com.example.login.data.enumss.FieldsStatus
import com.example.login.data.enumss.From
import com.example.login.data.repository.BirdRepository

class DefaultViewModel(): BaseViewModel() {
    val onSearchTrigger: MutableLiveData<FieldsStatus> =  MutableLiveData()
    val wifiState: MutableLiveData<Boolean> = MutableLiveData()

    val defaultRequestValue: MutableLiveData<String> = MutableLiveData()

    private fun fetchRequest(){
        if(defaultRequestValue.value.isNullOrEmpty()){
            onSearchTrigger.value = FieldsStatus.EMPTY
        } else {
            onSearchTrigger.value = FieldsStatus.FILLED
        }
    }

    fun onSearch(){
        fetchRequest()
    }
}