package com.example.login.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

object Repository {
    private val _userName = MutableLiveData<String>()
    val userName: LiveData<String>
    get() = _userName

    private val _userSurname = MutableLiveData<String>()
    val userSurname: LiveData<String>
        get() = _userSurname

    private val _userAge = MutableLiveData<Int>()
    val userAge: LiveData<Int>
        get() = _userAge

}