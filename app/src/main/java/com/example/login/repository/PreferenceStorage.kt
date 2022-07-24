package com.example.login.repository

import android.content.Context

const val PREFS_FILE_NAME = "storage"
const val LEVEL_KEY = "LEVEL_KEY"
const val CHARACTER_ID_KEY = "CHARACTER_KEY_ID"
const val CHARACTER_NAME_KEY = "CHARACTER_KEY_NAME"
const val AGE_SCREEN = "AGE_SCREEN"
const val LEVEL_SCREEN = "LEVEL_SCREEN"
const val CHARACTER_SCREEN = "CHARACTER_SCREEN"
class PreferenceStorage constructor(private val context: Context) {

    // data
    fun saveLevel(_value: Float) {
        val prefs = context.getSharedPreferences(PREFS_FILE_NAME, Context.MODE_PRIVATE)
        val prefsEdit = prefs.edit()

        prefsEdit.putFloat(LEVEL_KEY, _value)
        prefsEdit.apply()
    }

    fun getLevel(default: Float = 0f): Float {
        val prefs = context.getSharedPreferences(PREFS_FILE_NAME, Context.MODE_PRIVATE)
        return prefs.getFloat(LEVEL_KEY, default)
    }

    fun saveCharacterId(_value: Int?) {
        val prefs = context.getSharedPreferences(PREFS_FILE_NAME, Context.MODE_PRIVATE)
        val prefsEdit = prefs.edit()

        if (_value != null) {
            prefsEdit.putInt(CHARACTER_ID_KEY, _value)
        }
        prefsEdit.apply()
    }


    fun getCharacterId(default: Int = 0): Int {
        val prefs = context.getSharedPreferences(PREFS_FILE_NAME, Context.MODE_PRIVATE)
        return prefs.getInt(CHARACTER_ID_KEY, default)
    }

    fun saveCharacterName( _value: String) {
        val prefs = context.getSharedPreferences(PREFS_FILE_NAME, Context.MODE_PRIVATE)
        val prefsEdit = prefs.edit()

        prefsEdit.putString(CHARACTER_NAME_KEY, _value)
        prefsEdit.apply()
    }

    fun getCharacterName(default: String = ""): String? {
        val prefs = context.getSharedPreferences(PREFS_FILE_NAME, Context.MODE_PRIVATE)
        return prefs.getString(CHARACTER_NAME_KEY, default)
    }



    // screen states, is chose
    fun saveAgeScreen( _value: Boolean) {
        val prefs = context.getSharedPreferences(PREFS_FILE_NAME, Context.MODE_PRIVATE)
        val prefsEdit = prefs.edit()

        prefsEdit.putBoolean(AGE_SCREEN, _value)
        prefsEdit.apply()
    }
    fun checkAgeScreen(default: Boolean = false): Boolean {
        val prefs = context.getSharedPreferences(PREFS_FILE_NAME, Context.MODE_PRIVATE)
        return prefs.getBoolean(AGE_SCREEN, default)
    }


    fun saveLevelScreen(_value: Boolean) {
        val prefs = context.getSharedPreferences(PREFS_FILE_NAME, Context.MODE_PRIVATE)
        val prefsEdit = prefs.edit()

        prefsEdit.putBoolean(LEVEL_SCREEN, _value)
        prefsEdit.apply()
    }
    fun checkLevelScreen(default: Boolean = false): Boolean {
        val prefs = context.getSharedPreferences(PREFS_FILE_NAME, Context.MODE_PRIVATE)
        return prefs.getBoolean(LEVEL_SCREEN, default)
    }

    fun saveCharacterScreen(_value: Boolean) {
        val prefs = context.getSharedPreferences(PREFS_FILE_NAME, Context.MODE_PRIVATE)
        val prefsEdit = prefs.edit()

        prefsEdit.putBoolean(CHARACTER_SCREEN, _value)
        prefsEdit.apply()
    }
    fun checkCharacterScreen(default: Boolean = false): Boolean {
        val prefs = context.getSharedPreferences(PREFS_FILE_NAME, Context.MODE_PRIVATE)
        return prefs.getBoolean(CHARACTER_SCREEN, default)
    }

}
