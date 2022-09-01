package com.example.login.data.prefs

import android.content.Context

const val PREFS_FILE_NAME = "storage"
const val INTRODUCE_SCREENS_STATE = "LEVEL_KEY"
const val LOCATION_SCREEN_STATE = "LOCATION_SCREEN_KEY"
const val LOCATION_DATA_LATI = "LOCATION_DATA_LATI_KEY"
const val LOCATION_DATA_LONG = "LOCATION_DATA_LONG_KEY"

class PreferenceStorage constructor(private val context: Context) {

    //tabs screen state
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


    //location screen state
    fun saveLocationScreensState(_value: Boolean) {
        val prefs = context.getSharedPreferences(PREFS_FILE_NAME, Context.MODE_PRIVATE)
        val prefsEdit = prefs.edit()

        prefsEdit.putBoolean(LOCATION_SCREEN_STATE, _value)
        prefsEdit.apply()
    }

    fun getLocationScreensState(default: Boolean = false): Boolean {
        val prefs = context.getSharedPreferences(PREFS_FILE_NAME, Context.MODE_PRIVATE)
        return prefs.getBoolean(LOCATION_SCREEN_STATE, default)
    }

    //location
    fun saveLocationLatitude(_value: Long) {
        val prefs = context.getSharedPreferences(PREFS_FILE_NAME, Context.MODE_PRIVATE)
        val prefsEdit = prefs.edit()

        prefsEdit.putLong(LOCATION_DATA_LATI, _value)
        prefsEdit.apply()
    }

    fun getLocationLatitude(default: Long = 0): Long {
        val prefs = context.getSharedPreferences(PREFS_FILE_NAME, Context.MODE_PRIVATE)
        return prefs.getLong(LOCATION_DATA_LATI, default)
    }

    fun saveLocationLongitude(_value: Long) {
        val prefs = context.getSharedPreferences(PREFS_FILE_NAME, Context.MODE_PRIVATE)
        val prefsEdit = prefs.edit()

        prefsEdit.putLong(LOCATION_DATA_LONG, _value)
        prefsEdit.apply()
    }

    fun getLocationLongitude(default: Long = 0): Long {
        val prefs = context.getSharedPreferences(PREFS_FILE_NAME, Context.MODE_PRIVATE)
        return prefs.getLong(LOCATION_DATA_LONG, default)
    }
}