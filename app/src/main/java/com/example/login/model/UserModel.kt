package com.example.login.model

import android.text.TextUtils
import android.util.Patterns
import androidx.databinding.BaseObservable
import androidx.navigation.Navigation
import com.example.login.R

data class UserModel(
    private var name: String,
    private var surname: String,
    private var age: Int
) : BaseObservable() {

    fun isDataValid(): Int {
        when {
            name.isEmpty() && surname.isEmpty() -> return R.string.two_lines_empty

            name.isNotEmpty() && surname.isEmpty() -> return R.string.need_add_surname
            name.isEmpty() && surname.isNotEmpty() -> return R.string.need_add_name

            name.length > 12 -> return R.string.name_need_less_twelve
            name.length < 2 -> return R.string.name_need_more_one
            name.contains(' ') -> return R.string.name_no_probels

            surname.length > 12 -> return R.string.surname_need_not_more_twelve
            surname.length < 4 -> return R.string.surname_need_more_three
            surname.contains(' ') -> return R.string.surname_no_probels


            age < 16 -> return R.string.age_more_15
            age > 80 -> return R.string.age_less_100
            age == 0 -> return R.string.age_more_15

            else -> {
                return 1
            }
        }
    }

    fun getAge(): Int {
        return age
    }

    fun getName(): String {
        return name
    }

    fun getSurname(): String {
        return surname
    }

    fun setName(name: String) {
        this.name = name
    }

    fun setSurname(surname: String) {
        this.surname = surname
    }

    fun setAge(age: Int) {
        this.age = age
    }
}
