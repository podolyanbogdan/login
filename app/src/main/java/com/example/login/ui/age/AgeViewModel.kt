package com.example.login.ui.age

import androidx.lifecycle.MutableLiveData
import com.example.login.arch.BaseViewModel
import com.example.login.repository.MyRepository

class AgeViewModel(private val repo: MyRepository) : BaseViewModel() {
    val goNextScreen: MutableLiveData<Boolean> = MutableLiveData()
    private val isAgeAvailable: MutableLiveData<Boolean> = MutableLiveData()

    fun btnClicked() {
        if (isAgeAvailable.value == true) {
            repo.saveAgeScreenState(true)
            goNextScreen.value = true
        } else {
            goNextScreen.value = false
        }
    }

    fun onCheckedChanged(checked: Boolean) {
        isAgeAvailable.value = checked
    }
}