package com.example.login.ui.map

import androidx.lifecycle.MutableLiveData
import com.example.login.arch.BaseViewModel
import com.example.login.repository.MyRepository

class MapViewModel: BaseViewModel() {
    val level: MutableLiveData<String> = MutableLiveData()
    val character: MutableLiveData<String> = MutableLiveData()

    init {
        level.value = MyRepository.setLevel().toString()
        character.value = MyRepository.setCharacter()
    }
}