package com.example.login.data.repository

import com.example.login.data.prefs.PreferenceStorage

class PrefsRepository(
    private val prefs: PreferenceStorage
) {
    //tabs screen state
    fun checkScreenState(): Boolean {
        return prefs.getIntroduceScreensState()
    }

    fun saveScreenState(_value: Boolean) {
        prefs.saveIntroduceScreensState(_value)
    }

    // location screen state
    fun checkLocationState(): Boolean {
        return prefs.getLocationScreensState()
    }

    fun saveLocationState(_value: Boolean) {
        prefs.saveLocationScreensState(_value)
    }

    //location
    fun saveLocationLatitude(_value: Long) {
        prefs.saveLocationLatitude(_value)
    }

    fun getLocationLatitude(): Long {
        return prefs.getLocationLatitude()
    }

    fun saveLocationLongitude(_value: Long) {
        prefs.saveLocationLongitude(_value)
    }

    fun getLocationLongitude(): Long {
        return prefs.getLocationLongitude()
    }
}