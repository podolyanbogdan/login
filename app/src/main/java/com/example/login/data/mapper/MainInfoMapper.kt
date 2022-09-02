package com.example.login.data.mapper

import com.example.login.data.localeModels.MainInfoLocale
import com.example.login.data.modelsAPI.MainInfoAPI

class MainInfoMapper(
    private val feelsLikeMapper: FeelsLikeMapper,
    private val tempMapper: TempMapper,
    private val weatherMapper: WeatherMapper
) : ForecastMapper<MainInfoAPI, MainInfoLocale>() {
    override fun map(from: MainInfoAPI): MainInfoLocale {
        return MainInfoLocale(
            clouds = from.clouds,
            deg = from.deg,
            dt = from.dt,
            feelsLikeLocale = feelsLikeMapper.map(from.feelsLikeAPI),
            gust = from.gust,
            humidity = from.humidity,
            pop = from.pop,
            pressure = from.pressure,
            rain = from.rain,
            speed = from.speed,
            sunrise = from.sunrise,
            sunset = from.sunset,
            tempLocale = tempMapper.map(from.tempAPI),
            weatherLocale = weatherMapper.map(from.weatherAPI)
        )
    }
}