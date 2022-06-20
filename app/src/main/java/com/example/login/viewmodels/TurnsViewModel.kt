package com.example.login.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class TurnsViewModel : ViewModel() {
    var isCompFirst = MutableLiveData<Boolean>()

    fun personTurnClick() {
        isCompFirst.value = false
    }
    fun computerTurnClick() {
        isCompFirst.value = true
    }
}