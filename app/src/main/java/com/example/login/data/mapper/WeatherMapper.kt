package com.example.login.data.mapper

import com.example.login.data.localeModels.WeatherLocale
import com.example.login.data.modelsAPI.WeatherAPI

class WeatherMapper: ForecastMapper<WeatherAPI, WeatherLocale>() {
    override fun map(from: WeatherAPI): WeatherLocale {
        return WeatherLocale(
            description = from.description,
            icon = from.icon,
            id = from.id,
            main = from.main
        )
    }
}