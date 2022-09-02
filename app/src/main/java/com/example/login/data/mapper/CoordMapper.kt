package com.example.login.data.mapper

import com.example.login.data.localeModels.CoordLocale
import com.example.login.data.modelsAPI.CoordAPI

class CoordMapper : ForecastMapper<CoordAPI, CoordLocale>() {
    override fun map(from: CoordAPI): CoordLocale {
        return CoordLocale(
            lat = from.lat,
            lon = from.lon,
        )
    }
}
