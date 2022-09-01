package com.example.login.ui.mainScreens.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.login.arch.BaseViewModel
import com.example.login.arch.lifecycle.SingleLiveEvent
import com.example.login.arch.mapper.onFailure
import com.example.login.arch.mapper.onSuccess
import com.example.login.data.constants.Constants.FULL_DAY_FORMATTER
import com.example.login.data.constants.Constants.TIME_DAY_FORMATTER
import com.example.login.data.localeModels.HomeModel
import com.example.login.data.modelsAPI.DailyForecastAPI
import com.example.login.data.repository.ForecastRepository
import com.example.login.utils.AppUtils.Companion.currentDay
import com.example.login.utils.AppUtils.Companion.getDate
import kotlinx.coroutines.launch

class HomeViewModel(
    private val repo: ForecastRepository,
) : BaseViewModel() {
    private val currentDayNumber = currentDay()
    val weekForecastTrigger = SingleLiveEvent<Boolean>()
    val message: MutableLiveData<String> = MutableLiveData()
    val test: MutableLiveData<DailyForecastAPI> = MutableLiveData()
    val homeModel = HomeModel()

    init {
        viewModelScope.launch {
            if(!repo.isStop) repo.getCurrentCity()
            repo.isStop = true
            test.value = repo.setCityInfo()
            setForecastData(repo.setCityInfo())
        }
    }

    private fun setForecastData(resultResponse: DailyForecastAPI) {
        homeModel.cityName.set(resultResponse.city.name)
        homeModel.currentDay.set(getDate(FULL_DAY_FORMATTER))
        homeModel.currentTime.set(getDate(TIME_DAY_FORMATTER))
        homeModel.cityName.set(resultResponse.city.name)
        homeModel.countryName.set(resultResponse.city.country)
        homeModel.locationLon.set(resultResponse.city.coord.lon)
        homeModel.locationLat.set(resultResponse.city.coord.lat)
        homeModel.degrees.set(resultResponse.list[currentDayNumber].temp.day)
        homeModel.humidity.set(resultResponse.list[currentDayNumber].humidity)
        homeModel.windSpeed.set(resultResponse.list[currentDayNumber].speed)
        homeModel.weatherImg.set(resultResponse.list[currentDayNumber].weather[0].icon)
    }

    fun openDailyForecast() {
        weekForecastTrigger.value = true
    }
}