package com.example.login.data.modelsAPI


import com.google.gson.annotations.SerializedName

data class DailyForecastAPI(
    @SerializedName("city")
    val city: City,
    @SerializedName("cnt")
    val cnt: Int,
    @SerializedName("cod")
    val cod: String,
    @SerializedName("list")
    val list: List<MainInfo>,
    @SerializedName("message")
    val message: Double
)