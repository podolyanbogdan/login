package com.example.login.ui.mainScreens.weekForecast


import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.login.arch.BaseViewModel
import com.example.login.arch.lifecycle.SingleLiveEvent
import com.example.login.data.constants.Constants
import com.example.login.data.localeModels.HomeModel
import com.example.login.data.modelsAPI.City
import com.example.login.data.modelsAPI.DailyForecastAPI
import com.example.login.data.modelsAPI.MainInfo
import com.example.login.data.repository.ForecastRepository
import com.example.login.data.responseState.ResponseState
import com.example.login.utils.AppUtils
import com.example.login.utils.AppUtils.Companion.getDayName
import kotlinx.coroutines.launch
import retrofit2.Response

class WeekForecastViewModel(
    private val repo: ForecastRepository
): BaseViewModel() {
    val onBackTrigger = SingleLiveEvent<Boolean>()
    val message: MutableLiveData<String> = MutableLiveData()

    private val forecastResponse: MutableLiveData<ResponseState<DailyForecastAPI>> = MutableLiveData()
    val weatherData: MutableLiveData<List<MainInfo>> = MutableLiveData()
    val cityData: MutableLiveData<City> = MutableLiveData()

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
                cityData.value = resultResponse.city
                weatherData.value = resultResponse.list
            }
        } else {
            message.value = "No internet connection"
        }
        return ResponseState.Error(response.message())
    }

    fun onBack(){
        onBackTrigger.value = true
    }
}