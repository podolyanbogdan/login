package com.example.login.ui.introducingFragments.thirth

import androidx.lifecycle.MutableLiveData
import com.example.login.arch.BaseViewModel
import com.example.login.arch.lifecycle.SingleLiveEvent
import com.example.login.data.enumss.PermissionScreenState
import com.example.login.data.repository.PrefsRepository

class ThirdIntroduceViewModel(
    private val repo: PrefsRepository
): BaseViewModel() {
    val initEvent = SingleLiveEvent<Boolean>()

    val locationStatus:MutableLiveData<PermissionScreenState> = MutableLiveData()

    fun onGoToApp(){
        if(!repo.checkLocationState()){
           locationStatus.value = PermissionScreenState.DECLINED
        } else {
            locationStatus.value = PermissionScreenState.ACCEPT
        }
        repo.saveScreenState(true)
        initEvent.value = true
    }
}