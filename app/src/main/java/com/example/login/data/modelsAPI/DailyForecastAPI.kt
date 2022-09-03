package com.example.login.data.modelsAPI


import com.google.gson.annotations.SerializedName
import kotlinx.serialization.SerialName

import kotlinx.serialization.Serializable

@Serializable
data class DailyForecastAPI(
    @SerialName("city")
    val cityAPI: CityAPI = CityAPI(CoordAPI(0.0,0.0),"",0,"",0,0),
    @SerialName("cnt")
    val cnt: Int = 0,
    @SerialName("cod")
    var cod: String = "",
    @SerialName("list")
    val list: List<MainInfoAPI> = emptyList(),
    @SerialName("message")
    val message: Double = 0.0
)