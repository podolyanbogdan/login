package com.example.login.ui.wifi

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.login.arch.BaseViewModel


class WifiViewModel(wifiItemManager: WifiItemManager) : BaseViewModel() {
    private val _wifiList = MutableLiveData<ArrayList<WifiModel>>()
    val wifiList: LiveData<ArrayList<WifiModel>>
        get() = _wifiList

    private val _trigger = MutableLiveData<Boolean>()
    val trigger: LiveData<Boolean>
        get() = _trigger

    fun onClickWifi(){
        _trigger.value = true
    }

    init {
        wifiItemManager.wifiManager.startScan()
        _wifiList.postValue(wifiItemManager.getResultList())
    }
}
