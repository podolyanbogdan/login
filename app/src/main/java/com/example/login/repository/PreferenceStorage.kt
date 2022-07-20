package com.example.login.repository

import android.content.Context

const val PREFS_FILE_NAME = "storage"

class PreferenceStorage constructor(private val context: Context) {

    // data
    fun saveLevel(_key: String, _value: Float) {
        val prefs = context.getSharedPreferences(PREFS_FILE_NAME, Context.MODE_PRIVATE)
        val prefsEdit = prefs.edit()

        prefsEdit.putFloat(_key, _value)
        prefsEdit.apply()
    }

    fun getLevel(_key: String, default: Float = 0f): Float {
        val prefs = context.getSharedPreferences(PREFS_FILE_NAME, Context.MODE_PRIVATE)
        return prefs.getFloat(_key, default)
    }

    fun saveCharacterId(_key: String, _value: Int?) {
        val prefs = context.getSharedPreferences(PREFS_FILE_NAME, Context.MODE_PRIVATE)
        val prefsEdit = prefs.edit()

        if (_value != null) {
            prefsEdit.putInt(_key, _value)
        }
        prefsEdit.apply()
    }


    fun getCharacterId(_key: String, default: Int = 0): Int {
        val prefs = context.getSharedPreferences(PREFS_FILE_NAME, Context.MODE_PRIVATE)
        return prefs.getInt(_key, default)
    }

    fun saveCharacterName(_key: String, _value: String) {
        val prefs = context.getSharedPreferences(PREFS_FILE_NAME, Context.MODE_PRIVATE)
        val prefsEdit = prefs.edit()

        prefsEdit.putString(_key, _value)
        prefsEdit.apply()
    }

    fun getCharacterName(_key: String, default: String = ""): String? {
        val prefs = context.getSharedPreferences(PREFS_FILE_NAME, Context.MODE_PRIVATE)
        return prefs.getString(_key, default)
    }


    fun saveLocation(_key: String, _value: String) {
        val prefs = context.getSharedPreferences(PREFS_FILE_NAME, Context.MODE_PRIVATE)
        val prefsEdit = prefs.edit()

        prefsEdit.putString(_key, _value)
        prefsEdit.apply()
    }

    fun getLocation(_key: String, default: String = ""): String? {
        val prefs = context.getSharedPreferences(PREFS_FILE_NAME, Context.MODE_PRIVATE)
        return prefs.getString(_key, default)
    }


    // screen states, is chose
    fun saveAgeScreen(_key: String, _value: Boolean) {
        val prefs = context.getSharedPreferences(PREFS_FILE_NAME, Context.MODE_PRIVATE)
        val prefsEdit = prefs.edit()

        prefsEdit.putBoolean(_key, _value)
        prefsEdit.apply()
    }
    fun checkAgeScreen(_key: String, default: Boolean = false): Boolean {
        val prefs = context.getSharedPreferences(PREFS_FILE_NAME, Context.MODE_PRIVATE)
        return prefs.getBoolean(_key, default)
    }

    fun saveLevelScreen(_key: String, _value: Boolean) {
        val prefs = context.getSharedPreferences(PREFS_FILE_NAME, Context.MODE_PRIVATE)
        val prefsEdit = prefs.edit()

        prefsEdit.putBoolean(_key, _value)
        prefsEdit.apply()
    }
    fun checkLevelScreen(_key: String, default: Boolean = false): Boolean {
        val prefs = context.getSharedPreferences(PREFS_FILE_NAME, Context.MODE_PRIVATE)
        return prefs.getBoolean(_key, default)
    }

    fun saveCharacterScreen(_key: String, _value: Boolean) {
        val prefs = context.getSharedPreferences(PREFS_FILE_NAME, Context.MODE_PRIVATE)
        val prefsEdit = prefs.edit()

        prefsEdit.putBoolean(_key, _value)
        prefsEdit.apply()
    }
    fun checkCharacterScreen(_key: String, default: Boolean = false): Boolean {
        val prefs = context.getSharedPreferences(PREFS_FILE_NAME, Context.MODE_PRIVATE)
        return prefs.getBoolean(_key, default)
    }

}
