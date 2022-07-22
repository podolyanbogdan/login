package com.example.login.repository

import android.content.Context
import com.example.login.R
import com.example.login.constants.Constants
import com.example.login.data.UserModel
import com.example.login.json.JsonConverter
import com.example.login.utils.AppUtils
import com.google.android.gms.maps.model.LatLng

var list = mutableListOf<UserModel>()
var location = LatLng(0.0, 0.0)
class MyRepository(private val context: Context) {
    private val prefs: PreferenceStorage = PreferenceStorage(context)

    //level
    fun getLevel(): String {
        return prefs.getLevel().toInt().toString()
    }

    fun saveLevel(_value: Float) {
        prefs.saveLevel(_value)
    }

    //character
    fun getCharacter(): String? {
        return prefs.getCharacterName()
    }

    fun saveCharacterName(_value: String) {
        prefs.saveCharacterName(_value)
    }

    fun saveCharacterId(_value: Int) {
        prefs.saveCharacterId(_value)
    }

    fun getCharacterId(): Int {
        return prefs.getCharacterId()
    }

    fun getCharacterPicture(): Int {
        return when (prefs.getCharacterName()) {
            Constants.HERO -> return R.drawable.hero_icon1
            Constants.PLAYER -> return R.drawable.hero_icon2
            Constants.MASTER -> return R.drawable.hero_icon3
            else -> return R.drawable.ic_character
        }
    }

    //date
    fun getDate(): String {
        return AppUtils.getCurrentDate(Constants.DATE).toString()
    }

    //time
    fun getTime(): String {
        return AppUtils.getCurrentDate(Constants.TIME).toString()
    }

    //User Model
    fun getJsonModel(): UserModel? {
        return JsonConverter(context).gson.fromJson(
            JsonConverter(context).jsonString,
            UserModel::class.java
        )
    }

    //markers
    fun markersQuantity(markersList: UserModel?) {
        markersList?.let { list.add(it) }
    }

    fun getMarkersQuantity(): MutableList<UserModel> {
        return list
    }

    // location
    fun saveLocation(loc: LatLng){
        location = loc
    }
    fun getLocation(): LatLng{
        return location
    }

    //save screen states
    fun saveAgeScreenState(state: Boolean) {
        prefs.saveAgeScreen(state)
    }

    fun saveLevelScreenState(state: Boolean) {
        prefs.saveLevelScreen(state)
    }

    fun saveCharacterScreenState(state: Boolean) {
        prefs.saveCharacterScreen(state)
    }

    //checks screen states
    fun getAgeScreenState(): Boolean {
        return prefs.checkAgeScreen()
    }

    fun getLevelScreenState(): Boolean {
        return prefs.checkLevelScreen()
    }

    fun getCharacterScreenState(): Boolean {
        return prefs.checkCharacterScreen()
    }


    //split data for marker
    fun splitUserDataMapMarker(): String {
        return "${getCharacter()} level ${getLevel()}"
    }
}