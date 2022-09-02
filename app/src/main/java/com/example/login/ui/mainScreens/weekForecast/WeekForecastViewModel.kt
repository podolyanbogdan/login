package com.example.login.ui.mainScreens.weekForecast


import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.login.arch.BaseViewModel
import com.example.login.arch.lifecycle.SingleLiveEvent
import com.example.login.data.localeModels.CityLocale
import com.example.login.data.localeModels.DailyForecastLocale
import com.example.login.data.localeModels.MainInfoLocale
import com.example.login.data.modelsAPI.CityAPI
import com.example.login.data.modelsAPI.DailyForecastAPI
import com.example.login.data.modelsAPI.MainInfoAPI
import com.example.login.data.repository.ForecastStorage
import kotlinx.coroutines.launch

class WeekForecastViewModel(
    private val repo: ForecastStorage
): BaseViewModel() {
    val onBackTrigger = SingleLiveEvent<Boolean>()
    val message: MutableLiveData<String> = MutableLiveData()
    val weatherData: MutableLiveData<List<MainInfoLocale>> = MutableLiveData()
    val cityAPIData: MutableLiveData<CityLocale> = MutableLiveData()

    init {
        viewModelScope.launch {
            setForecastData(repo.setCityInfo())
            message.value = repo.errorMessage
        }
    }

    private fun setForecastData(item: DailyForecastLocale){
        cityAPIData.value = item.cityLocale
        weatherData.value = item.list
    }

    fun onBack(){
        onBackTrigger.value = true
    }
}