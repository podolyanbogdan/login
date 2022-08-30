package com.example.login.data.repository

import com.example.login.data.constants.Constants.API_KEY
import com.example.login.retrofit.RetrofitInstance

class ForecastRepository(
) {

    suspend fun getDailyForecast() =
        RetrofitInstance.api.loadForecastWeekly("Kyiv",API_KEY)
}