package com.example.login.ui.mainScreens.permissionScreen

import androidx.lifecycle.MutableLiveData
import com.example.login.arch.BaseViewModel
import com.example.login.data.repository.PrefsRepository

class PermissionLocaleViewModel(
    private val prefsRepo: PrefsRepository
): BaseViewModel() {
    val locationTrigger: MutableLiveData<Boolean> = MutableLiveData()

    fun setLongitude(data: Long){
        prefsRepo.saveLocationLongitude(data)
    }

    fun setLatitude(data: Long){
        prefsRepo.saveLocationLatitude(data)
    }

    fun onLocationShared(){
        locationTrigger.value = true
        prefsRepo.saveLocationState(true)
    }
}