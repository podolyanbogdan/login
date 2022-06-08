package com.example.login.viewmodel

import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.Navigation
import com.example.login.R
import com.example.login.model.UserModel
import java.util.regex.Matcher
import java.util.regex.Pattern

class UserAuthorizationViewModel: ViewModel() {
     val userNameModel: MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }
     val userSurnameModel: MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }
     val userAgeModel: MutableLiveData<Int> by lazy {
        MutableLiveData<Int>()
    }

    val correctAnswerModel: MutableLiveData<Int> by lazy {
        MutableLiveData<Int>()
    }
    val unCorrectAnswerModel: MutableLiveData<Int> by lazy {
        MutableLiveData<Int>()
    }

    private val statusMessage = MutableLiveData<Event<Int>>()
    val message : LiveData<Event<Int>>
        get() = statusMessage

    fun confirmUserInfo(userModel: UserModel, view: View){
        val allSymbols: Pattern = Pattern.compile("[^A-Za-z0-9-А-Я-а-я]")
        val matcherByName: Matcher = allSymbols.matcher(userModel.name)
        val matcherBySurname: Matcher = allSymbols.matcher(userModel.surname)
        val nameHasSpecialSymbols: Boolean = matcherByName.find()
        val surnameHasSpecialSymbols: Boolean = matcherBySurname.find()
        val normal = true
        when{
            userModel.name.isEmpty() && userModel.surname.isEmpty() -> statusMessage.value = Event(R.string.two_lines_empty)

            userModel.name.isNotEmpty() && userModel.surname.isEmpty() -> statusMessage.value = Event(R.string.need_add_surname)
            userModel.name.isEmpty() && userModel.surname.isNotEmpty() -> statusMessage.value = Event(R.string.need_add_name)

            userModel.name.length > 12 -> statusMessage.value = Event(R.string.name_need_less_twelve)
            userModel.name.length < 2 -> statusMessage.value = Event(R.string.name_need_more_one)
            userModel.name.contains(' ') -> statusMessage.value = Event(R.string.name_no_probels)
            nameHasSpecialSymbols -> statusMessage.value = Event(R.string.name_no_symbol)

            userModel.surname.length > 12 -> statusMessage.value = Event(R.string.surname_need_not_more_twelve)
            userModel.surname.length < 4 -> statusMessage.value = Event(R.string.surname_need_more_three)
            userModel.surname.contains(' ') -> statusMessage.value = Event(R.string.surname_no_probels)
            surnameHasSpecialSymbols -> statusMessage.value = Event(R.string.surname_no_symbol)

            userModel.age < 16 -> statusMessage.value = Event(R.string.age_more_15)
            userModel.age > 80 -> statusMessage.value = Event(R.string.age_less_100)
            userModel.age == 0 -> statusMessage.value = Event(R.string.age_more_15)

            else -> {
                statusMessage.value = Event(R.string.login_successful)
                userNameModel.value = userModel.name
                userSurnameModel.value = userModel.surname
                userAgeModel.value = userModel.age
                view.let { Navigation.findNavController(it).navigate(R.id.fromIntroduceToQuestions) }
            }
        }
    }
}