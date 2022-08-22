package com.example.login.ui.screens.details

import androidx.lifecycle.MutableLiveData
import com.example.login.arch.BaseViewModel
import com.example.login.arch.lifecycle.SingleLiveEvent
import com.example.login.data.enumss.MusicStatus
import com.example.login.data.repository.BirdRepository

class DetailsViewModel(
    repo: BirdRepository
): BaseViewModel() {
    val onCloseTrigger = SingleLiveEvent<Unit>()
    val musicStatus: MutableLiveData<MusicStatus> = MutableLiveData()
    val birdDetailModel = repo.setBirdDetail()

    fun onClose(){
        onCloseTrigger.value = Unit
    }

    fun onPlayMusic(){
        musicStatus.value = MusicStatus.PLAY
    }

    fun onStopMusic(){
        musicStatus.value = MusicStatus.STOP
    }

    fun downloadMusic(){
        musicStatus.value = MusicStatus.DOWNLOAD
    }
}