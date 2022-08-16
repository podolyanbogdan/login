package com.example.login.ui.home

import androidx.lifecycle.MutableLiveData
import com.example.login.arch.BaseViewModel
import com.example.login.data.enums.Direction
import com.example.login.data.enums.PermissionStatus

class HomeViewModel: BaseViewModel() {
    val direction: MutableLiveData<Direction> = MutableLiveData()
    val permissionStatus: MutableLiveData<PermissionStatus> = MutableLiveData()

    fun setPermissionStatus(status: PermissionStatus){
        permissionStatus.value = status
    }

    fun toEditPhoto(){
        direction.value = Direction.EDIT
    }

    fun openSettings(){
        direction.value = Direction.SETTINGS
    }
}