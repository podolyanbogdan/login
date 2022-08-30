package com.example.login.ui.mainScreens.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.login.arch.BaseViewModel
import com.example.login.arch.lifecycle.SingleLiveEvent
import com.example.login.data.constants.Constants.CLOUD
import com.example.login.data.constants.Constants.FULL_DAY_FORMATTER
import com.example.login.data.constants.Constants.NOT_FOUND
import com.example.login.data.constants.Constants.SUN
import com.example.login.data.constants.Constants.TIME_DAY_FORMATTER
import com.example.login.data.localeModels.HomeModel
import com.example.login.data.modelsAPI.DailyForecastAPI
import com.example.login.data.repository.ForecastRepository
import com.example.login.data.responseState.ResponseState
import com.example.login.utils.AppUtils.Companion.currentDay
import com.example.login.utils.AppUtils.Companion.getDate
import kotlinx.coroutines.launch
import retrofit2.Response

class HomeViewModel(
    private val repo: ForecastRepository
) : BaseViewModel() {
    private val currentDayNumber = currentDay()
    val weekForecastTrigger = SingleLiveEvent<Boolean>()
    val message: MutableLiveData<String> = MutableLiveData()

    private val forecastResponse: MutableLiveData<ResponseState<DailyForecastAPI>> = MutableLiveData()
    val homeModel = HomeModel()

    init {
        getForecastResponse()
    }

    private fun getForecastResponse() = viewModelScope.launch {
        forecastResponse.postValue(ResponseState.Loading())
        val response = repo.getDailyForecast()
        forecastResponse.postValue(handleForecastResponse(response))
    }

    private fun handleForecastResponse(response: Response<DailyForecastAPI>): ResponseState<DailyForecastAPI> {
        if (response.isSuccessful) {
            response.body()?.let { resultResponse ->
                setForecastData(resultResponse)
            }
        } else {
            message.value = "No internet connection"
        }
        return ResponseState.Error(response.message())
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