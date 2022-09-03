package com.example.login.data.mapper

import com.example.login.data.localeModels.CityLocale
import com.example.login.data.modelsAPI.CityAPI

class CityMapper(
    private val coordMapper: CoordMapper
) : ForecastMapper<CityAPI, CityLocale>() {
    override fun map(from: CityAPI): CityLocale {
        return CityLocale(
            coordLocale = coordMapper.map(from.coordAPI),
            country = from.country,
            id = from.id,
            name = from.name,
            population = from.population,
            timezone = from.timezone
        )
    }
}