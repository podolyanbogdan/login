package com.example.login.data.modelsAPI


import com.google.gson.annotations.SerializedName

data class DailyForecastAPI(
    @SerializedName("city")
    val cityAPI: CityAPI = CityAPI(CoordAPI(0.0,0.0),"",0,"",0,0),
    @SerializedName("cnt")
    val cnt: Int = 0,
    @SerializedName("cod")
    var cod: String = "",
    @SerializedName("list")
    val list: List<MainInfoAPI> = emptyList(),
    @SerializedName("message")
    val message: Double = 0.0
)