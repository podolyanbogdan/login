package com.example.login.repository

import android.os.Handler
import android.os.Looper
import androidx.lifecycle.MutableLiveData
import com.example.login.constants.Constants.TAG_TO_FIND
import com.example.login.constants.Constants.URL_PAGE
import com.example.login.data.House
import com.example.login.data.HouseInfo
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import org.jsoup.Jsoup
import java.io.IOException

var jsonHouseString = ""
var newHouseList = mutableListOf<House>()
class SensorRepository() {
    var sensors: MutableLiveData<List<House>> = MutableLiveData()

    //parse html page
    fun parseWebPage(): String {
        var resultJsonString = ""
        try {
            val url = URL_PAGE
            val html = Jsoup.connect(url).get()
            val pTag = html.select(TAG_TO_FIND)[4]
                .text()
            resultJsonString = pTag
                .replace("”", "\"")
                .replace("“", "\"")

        } catch (e: IOException) {
            e.printStackTrace()
        }
        return resultJsonString
    }


    //get json Sensor String
    fun getJsonHouseString(value: String) {
        jsonHouseString = value
    }
    //SET json Sensor String
    private fun setJsonHouseString(): String {
        return jsonHouseString
    }

    // init recycler sensors list
    private var sensorsList = mutableListOf<House>()

    // fetch Sensors to list
    private fun setJsonSensorsList(): MutableList<House> {
        val tempList = mutableListOf<House>()
        val jsonString = Json.decodeFromString<HouseInfo>(setJsonHouseString())
        jsonString.house.forEach { house ->
            tempList.add(
                House(
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
            sensorsList = (setJsonSensorsList() + setNewSensorsList()) as MutableList<House>
            sensors.value = sensorsList
        }, 2000)
    }

    // add new sensor
    fun addNewSensorsList(houseModel: House) {
        sensorsList.add(houseModel)
        newHouseList.add(houseModel)
    }
    private fun setNewSensorsList(): MutableList<House> {
        return newHouseList
    }

}