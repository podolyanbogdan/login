package com.example.login.repository

import android.os.Handler
import android.os.Looper
import androidx.lifecycle.MutableLiveData
import com.example.login.constants.Constants.DATA_ENDED
import com.example.login.constants.Constants.DATA_STARED
import com.example.login.data.FakeLocaleSync
import com.example.login.data.House
import com.example.login.data.HouseInfo
import com.example.login.jsoup.JsoupParser
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json


var jsonHouseString = ""
// init recycler sensors list
private var sensorsList = mutableListOf<House>()
var localeSensorList = mutableListOf<House>()
class SensorRepository {
    var sensors: MutableLiveData<MutableList<House>> = MutableLiveData()

    //parse html page
    fun parseWebPage(): String {
        return JsoupParser().parseWebPage()
    }

    //correct json string
    private fun correctJsonString(): String {
        return setJsonHouseString()
            .replace("”", "\"")
            .replace("“", "\"")
            .subSequence(
                setJsonHouseString().indexOf(DATA_STARED) + DATA_STARED.length,
                setJsonHouseString().indexOf(DATA_ENDED)
            ).toString()
    }

    //get json string to viewModel
    fun getJsonHouseString(value: String) {
        jsonHouseString = value
    }

    //set json string from viewModel
    private fun setJsonHouseString(): String {
        return jsonHouseString
    }


    // fetch Sensors to list
    private fun setJsonSensorsList(): MutableList<House> {
        val tempList = mutableListOf<House>()
        val jsonString = Json.decodeFromString<HouseInfo>(correctJsonString())
        jsonString.house.forEachIndexed { index, house ->
            tempList.add(
                House(
                    id = index,
                    room = house.room,
                    subtype = house.subtype,
                    type = house.type,
                    value = house.value
                )
            )
        }
        return tempList
    }

    // add list to recycler
    fun syncSensors() {
        Handler(Looper.getMainLooper()).postDelayed({
            sensorsList = ((setJsonSensorsList() + setLocaleSensorsList()).toMutableList())
            sensors.value = sensorsList
            updateLocaleSensor()
        }, 1500)
    }

    // add new sensor
    fun addNewSensorsList(houseModel: House) {
        sensorsList.add(houseModel)
        localeSensorList.add(houseModel)
    }

    // update locale senor when sync
    private fun updateLocaleSensor() {
        localeSensorList.forEach { house ->
            house.value = getRandomValue()
        }
    }

    // set locale sensors
    private fun setLocaleSensorsList(): MutableList<House> {
        return localeSensorList
    }

    fun deleteItem(sensor: House){
        sensorsList.remove(sensor)
    }

    //random value for sync locale sensors
    fun getRandomValue(): String {
        return when ((0..5).random()) {
            0 -> FakeLocaleSync.FAKE_PIC1.data
            1 -> FakeLocaleSync.F20.data
            2 -> FakeLocaleSync.OFF.data
            3 -> FakeLocaleSync.SWITCH.data
            4 -> FakeLocaleSync.SEVEN.data
            5 -> FakeLocaleSync.FAKE_PIC2.data
            else -> FakeLocaleSync.FIVE.data
        }
    }

}