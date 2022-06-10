package com.example.login.viewmodel

import android.text.Editable
import android.text.TextWatcher
import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.login.interfaceLogin.LoginResultCallBacks
import com.example.login.model.UserModel

class UserLoginViewModel(
    private val listener: LoginResultCallBacks
) : ViewModel() {
    private var userModel: UserModel = UserModel("", "", 0)

    val nameTextWatcher: TextWatcher
        get() = object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun afterTextChanged(s: Editable?) {
                userModel.setName(s.toString())
            }

        }

    val surnameTextWatcher: TextWatcher
        get() = object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun afterTextChanged(s: Editable?) {
                userModel.setSurname(s.toString())
            }

        }

    val ageTextWatcher: TextWatcher
        get() = object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun afterTextChanged(s: Editable?) {
                userModel.setAge(s.toString().toInt())
            }

        }

    val isNavigate: MutableLiveData<Int> by lazy {
        MutableLiveData<Int>()
    }


    fun onLoginClicked(view: View) {
        listener.onResultMessage(userModel.isDataValid())
        if(userModel.isDataValid() == 1){
            isNavigate.value = 1
        }
    }
}