package com.example.login.ui.mainScreens.weekForecast


import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.login.arch.BaseViewModel
import com.example.login.arch.lifecycle.SingleLiveEvent
import com.example.login.arch.mapper.onFailure
import com.example.login.arch.mapper.onSuccess
import com.example.login.data.localeModels.HomeModel
import com.example.login.data.modelsAPI.City
import com.example.login.data.modelsAPI.DailyForecastAPI
import com.example.login.data.modelsAPI.MainInfo
import com.example.login.data.repository.ForecastRepository
import kotlinx.coroutines.launch

class WeekForecastViewModel(
    private val repo: ForecastRepository
): BaseViewModel() {
    val onBackTrigger = SingleLiveEvent<Boolean>()
    val message: MutableLiveData<String> = MutableLiveData()
    val weatherData: MutableLiveData<List<MainInfo>> = MutableLiveData()
    val cityData: MutableLiveData<City> = MutableLiveData()

    init {
        viewModelScope.launch {
            setForecastData(repo.setCityInfo())
        }
    }

    private fun setForecastData(item: DailyForecastAPI){
        cityData.value = item.city
        weatherData.value = item.list
    }

    fun onBack(){
        onBackTrigger.value = true
    }
}