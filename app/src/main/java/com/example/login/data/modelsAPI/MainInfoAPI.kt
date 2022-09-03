package com.example.login.data.modelsAPI


import com.google.gson.annotations.SerializedName
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MainInfoAPI(
    @SerialName("clouds")
    val clouds: Int,
    @SerialName("deg")
    val deg: Int,
    @SerialName("dt")
    val dt: Int,
    @SerialName("feels_like")
    val feelsLikeAPI: FeelsLikeAPI,
    @SerialName("gust")
    val gust: Double,
    @SerialName("humidity")
    val humidity: Int,
    @SerialName("pop")
    val pop: Double,
    @SerialName("pressure")
    val pressure: Int,
    @SerialName("rain")
    val rain: Double = 0.0,
    @SerialName("speed")
    val speed: Double,
    @SerialName("sunrise")
    val sunrise: Int,
    @SerialName("sunset")
    val sunset: Int,
    @SerialName("temp")
    val tempAPI: TempAPI,
    @SerialName("weather")
    val weatherAPI: List<WeatherAPI>,
)