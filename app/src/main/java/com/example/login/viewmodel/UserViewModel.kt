package com.example.login.viewmodel

import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.Navigation
import com.example.login.R
import java.util.regex.Matcher
import java.util.regex.Pattern

class UserViewModel: ViewModel() {
    val userNameModel: MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }

    private val statusMessage = MutableLiveData<Event<Int>>()
    val message : LiveData<Event<Int>>
    get() = statusMessage

    fun successfulLogin(userModel: UserModel, view: View) {
        val allSymbols: Pattern = Pattern.compile("[^A-Za-z0-9]")
        val matcherByPass: Matcher = allSymbols.matcher(userModel.password)
        val matcherByLogin: Matcher = allSymbols.matcher(userModel.login)
        val passHasSpecialSymbols: Boolean = matcherByPass.find()
        val loginHasSpecialSymbols: Boolean = matcherByLogin.find()
        when {
            userModel.password.isEmpty() && userModel.login.isEmpty() -> statusMessage.value = Event(R.string.two_lines_empty)
            userModel.password.isNotEmpty() && userModel.login.isEmpty() -> statusMessage.value = Event(R.string.need_add_login)
            userModel.password.isEmpty() && userModel.login.isNotEmpty() -> statusMessage.value = Event(R.string.need_add_pass)

            userModel.password.length > 10 -> statusMessage.value = Event(R.string.pass_need_more_ten)
            userModel.password.length < 5 -> statusMessage.value = Event(R.string.pass_need_not_more_ten)
            userModel.password.contains(' ') -> statusMessage.value = Event(R.string.pass_no_probels)
            passHasSpecialSymbols -> statusMessage.value = Event(R.string.pass_no_symbol)

            userModel.login.length > 10 -> statusMessage.value = Event(R.string.login_need_not_more_ten)
            userModel.login.length < 5 -> statusMessage.value = Event(R.string.login_need_more_four)
            userModel.login.contains(' ') -> statusMessage.value = Event(R.string.login_no_probels)
            loginHasSpecialSymbols -> statusMessage.value = Event(R.string.login_no_symbol)

            userModel.login == "admin" && userModel.password == "admin" -> {
                statusMessage.value = Event(R.string.login_successful)
                view.let { Navigation.findNavController(it).navigate(R.id.fromLogInToHello) }
                userNameModel.value = userModel.login
            }
            else -> statusMessage.value = Event(R.string.wrong_data)
        }
    }

}