package com.example.login.data.mapper

import com.example.login.data.localeModels.TempLocale
import com.example.login.data.modelsAPI.TempAPI

class TempMapper : ForecastMapper<TempAPI, TempLocale>() {
    override fun map(from: TempAPI): TempLocale {
        return TempLocale(
            day = from.day,
            eve = from.eve,
            max = from.max,
            min = from.min,
            morn = from.morn,
            night = from.night
        )
    }
}