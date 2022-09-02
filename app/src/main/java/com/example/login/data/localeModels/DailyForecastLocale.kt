package com.example.login.data.localeModels


data class DailyForecastLocale(
    val cityLocale: CityLocale = CityLocale(CoordLocale(0.0,0.0),"",0,"",0,0),
    val cnt: Int = 0,
    var cod: String = "",
    val list: List<MainInfoLocale> = emptyList(),
    val message: Double = 0.0
)