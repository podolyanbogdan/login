package com.example.login.ui.screens.details

import com.example.login.arch.BaseViewModel
import com.example.login.arch.lifecycle.SingleLiveEvent
import com.example.login.data.BirdModel
import com.example.login.data.repository.BirdRepository

class DetailsViewModel(
    private val repo: BirdRepository
): BaseViewModel() {
    val onCloseTrigger = SingleLiveEvent<Unit>()
    val birdDetailModel = repo.setBirdDetail()

    fun onClose(){
        onCloseTrigger.value = Unit
    }
}