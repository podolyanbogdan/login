package com.example.login.ui.mainScreens.cities

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.login.arch.BaseViewModel
import com.example.login.data.localeModels.DailyForecastLocale
import com.example.login.data.modelsAPI.DailyForecastAPI
import com.example.login.data.repository.ForecastStorage
import com.example.login.ui.mainScreens.cities.adapter.RecyclerClick
import kotlinx.coroutines.launch

class CitiesViewModel(
    private val repo: ForecastStorage
) : BaseViewModel(), RecyclerClick {
    val addCityTrigger: MutableLiveData<Boolean> = MutableLiveData()
    val showCityInfoTrigger: MutableLiveData<Boolean> = MutableLiveData()
    val message: MutableLiveData<String> = MutableLiveData()
    var cities: MutableLiveData<MutableList<DailyForecastLocale>> = MutableLiveData()
    var citiesRepoList: MutableLiveData<MutableList<String>> = MutableLiveData()

    init {
        fetchData()
        citiesRepoList.value = repo.cityList
    }

    fun fetchData() {
        viewModelScope.launch {
            cities.value = repo.getForecastData()
            message.value = repo.errorMessage
        }
    }

    fun onAddCity() {
        addCityTrigger.value = true
    }

    override fun showCityInfo(item: DailyForecastLocale) {
        repo.getCityInfo(item)
        showCityInfoTrigger.value = true
    }
}