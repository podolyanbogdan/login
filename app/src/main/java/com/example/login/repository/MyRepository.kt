package com.example.login.repository

import android.content.Context
import com.example.login.constants.Constants
import com.example.login.constants.Constants.CHARACTER_NAME_KEY
import com.example.login.constants.Constants.LEVEL_KEY
import com.example.login.data.UserModel
import com.example.login.utils.AppUtils

class MyRepository(private val context: Context) {

    private fun getLevel(): String {
        return PreferenceStorage(context).getLevel(LEVEL_KEY).toInt().toString()
    }

    private fun getCharacter(): String? {
        return PreferenceStorage(context).getCharacterName(CHARACTER_NAME_KEY)
    }

    fun splitUserData(): String {
        return "${getCharacter()} level ${getLevel()}"
    }
}