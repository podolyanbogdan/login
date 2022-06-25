package com.example.login.ui.wifi

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.login.arch.BaseViewModel
import kotlinx.coroutines.launch


class WifiViewModel(wifiItemManager: WifiItemManager) : BaseViewModel() {
    private val _wifiList = MutableLiveData<ArrayList<WifiModel>>()
    val wifiList: LiveData<ArrayList<WifiModel>>
        get() = _wifiList


    init {
        wifiItemManager.wifiManager.startScan()
        _wifiList.postValue(wifiItemManager.getResultList())
    }
}
