package com.example.login.ui.mainScreens.cities.adapter

import com.example.login.data.modelsAPI.DailyForecastAPI

interface RecyclerClick {
    fun showCityInfo(item: DailyForecastAPI)
}