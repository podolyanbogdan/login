package com.example.login.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class UserViewModel: ViewModel() {
    val userNameModel: MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }
}