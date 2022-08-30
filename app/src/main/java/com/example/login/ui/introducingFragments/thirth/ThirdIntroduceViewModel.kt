package com.example.login.ui.introducingFragments.thirth

import com.example.login.arch.BaseViewModel
import com.example.login.arch.lifecycle.SingleLiveEvent
import com.example.login.data.repository.PrefsRepository

class ThirdIntroduceViewModel(
    private val repo: PrefsRepository
): BaseViewModel() {
    val initEvent = SingleLiveEvent<Boolean>()

    fun onGoToApp(){
        repo.saveScreenState(true)
        initEvent.value = true
    }
}