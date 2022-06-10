package com.example.login.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.login.interfaceLogin.LoginResultCallBacks

class LoginViewModelFactory(
    private val listener: LoginResultCallBacks
) : ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return LoginViewModelFactory(listener) as T
    }
}