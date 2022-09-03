package com.example.login.data.localeModels


data class MainInfoLocale(
    val clouds: Int,
    val deg: Int,
    val dt: Int,
    val feelsLikeLocale: FeelsLikeLocale,
    val gust: Double,
    val humidity: Int,
    val pop: Double,
    val pressure: Int,
    val rain: Double,
    val speed: Double,
    val sunrise: Int,
    val sunset: Int,
    val tempLocale: TempLocale,
    val weatherLocale: List<WeatherLocale>,
)