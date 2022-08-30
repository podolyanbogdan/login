package com.example.login.data.prefs

import android.content.Context

const val PREFS_FILE_NAME = "storage"
const val INTRODUCE_SCREENS_STATE = "LEVEL_KEY"

class PreferenceStorage constructor(private val context: Context) {

    fun saveIntroduceScreensState(_value: Boolean) {
        val prefs = context.getSharedPreferences(PREFS_FILE_NAME, Context.MODE_PRIVATE)
        val prefsEdit = prefs.edit()

        prefsEdit.putBoolean(INTRODUCE_SCREENS_STATE, _value)
        prefsEdit.apply()
    }

    fun getIntroduceScreensState(default: Boolean = false): Boolean {
        val prefs = context.getSharedPreferences(PREFS_FILE_NAME, Context.MODE_PRIVATE)
        return prefs.getBoolean(INTRODUCE_SCREENS_STATE, default)
    }
}