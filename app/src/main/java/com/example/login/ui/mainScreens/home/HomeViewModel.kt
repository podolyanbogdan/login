package com.example.login.ui.mainScreens.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.login.arch.BaseViewModel
import com.example.login.arch.lifecycle.SingleLiveEvent
import com.example.login.data.constants.Constants.FULL_DAY_FORMATTER
import com.example.login.data.constants.Constants.TIME_DAY_FORMATTER
import com.example.login.data.localeModels.DailyForecastLocale
import com.example.login.data.localeModels.HomeModel
import com.example.login.data.modelsAPI.DailyForecastAPI
import com.example.login.data.repository.ForecastStorage
import com.example.login.utils.AppUtils.Companion.currentDay
import com.example.login.utils.AppUtils.Companion.getDate
import kotlinx.coroutines.launch

class HomeViewModel(
    private val repo: ForecastStorage,
) : BaseViewModel() {
    private val currentDayNumber = currentDay()
    val weekForecastTrigger = SingleLiveEvent<Boolean>()
    val message: MutableLiveData<String> = MutableLiveData()
    val homeModel = HomeModel()

    init {
        viewModelScope.launch {
            if(!repo.isStop) repo.getCurrentCity()
            repo.isStop = true
            message.value = repo.errorMessage
            setForecastData(repo.setCityInfo())
        }
    }

    private fun setForecastData(resultResponse: DailyForecastLocale) {
        homeModel.cityName.set(resultResponse.cityLocale.name)
        homeModel.currentDay.set(getDate(FULL_DAY_FORMATTER))
        homeModel.currentTime.set(getDate(TIME_DAY_FORMATTER))
        homeModel.cityName.set(resultResponse.cityLocale.name)
        homeModel.countryName.set(resultResponse.cityLocale.country)
        homeModel.locationLon.set(resultResponse.cityLocale.coordLocale.lon)
        homeModel.locationLat.set(resultResponse.cityLocale.coordLocale.lat)
        homeModel.degrees.set(resultResponse.list[currentDayNumber].tempLocale.day)
        homeModel.humidity.set(resultResponse.list[currentDayNumber].humidity)
        homeModel.windSpeed.set(resultResponse.list[currentDayNumber].speed)
        homeModel.weatherImg.set(resultResponse.list[currentDayNumber].weatherLocale[0].icon)
    }

    fun openDailyForecast() {
        weekForecastTrigger.value = true
    }
}