package com.example.login.data.repository

import com.example.login.data.prefs.PreferenceStorage

class PrefsRepository(
    private val prefs: PreferenceStorage
) {
    fun checkScreenState(): Boolean {
        return prefs.getIntroduceScreensState()
    }

    fun saveScreenState(_value: Boolean) {
        prefs.saveIntroduceScreensState(_value)
    }
}