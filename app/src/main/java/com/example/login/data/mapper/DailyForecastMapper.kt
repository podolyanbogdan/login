package com.example.login.data.mapper

import com.example.login.data.localeModels.DailyForecastLocale
import com.example.login.data.modelsAPI.DailyForecastAPI

class DailyForecastMapper(
    private val cityMapper: CityMapper,
    private val mainInfoMapper: MainInfoMapper
) : ForecastMapper<DailyForecastAPI, DailyForecastLocale>() {
    override fun map(from: DailyForecastAPI): DailyForecastLocale {
        return DailyForecastLocale(
            cityLocale = cityMapper.map(from.cityAPI),
            cnt = from.cnt,
            cod = from.cod,
            list = mainInfoMapper.map(from.list),
            message = from.message
        )
    }
}