package com.example.login.ui.mainScreens.cities

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.login.arch.BaseViewModel
import com.example.login.arch.lifecycle.SingleLiveEvent
import com.example.login.data.modelsAPI.City
import com.example.login.data.modelsAPI.DailyForecastAPI
import com.example.login.data.modelsAPI.MainInfo
import com.example.login.data.repository.ForecastRepository
import com.example.login.data.responseState.ResponseState
import kotlinx.coroutines.launch
import retrofit2.Response

class CitiesViewModel(
    private val repo: ForecastRepository
): BaseViewModel() {
    private val forecastResponse: MutableLiveData<ResponseState<DailyForecastAPI>> = MutableLiveData()
    val forecastData: MutableLiveData<List<DailyForecastAPI>> = MutableLiveData()

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
                forecastData.value = listOf(resultResponse)
            }
        }
        return ResponseState.Error(response.message())
    }
}