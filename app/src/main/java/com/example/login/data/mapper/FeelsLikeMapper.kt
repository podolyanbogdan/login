package com.example.login.data.mapper

import com.example.login.data.localeModels.FeelsLikeLocale
import com.example.login.data.modelsAPI.FeelsLikeAPI

class FeelsLikeMapper: ForecastMapper<FeelsLikeAPI, FeelsLikeLocale>() {
    override fun map(from: FeelsLikeAPI): FeelsLikeLocale {
        return FeelsLikeLocale(
            day = from.day,
            eve = from.eve,
            morn = from.morn,
            night = from.night
        )
    }
}