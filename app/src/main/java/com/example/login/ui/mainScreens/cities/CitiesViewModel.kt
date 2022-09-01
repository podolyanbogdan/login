package com.example.login.ui.mainScreens.cities

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.login.arch.BaseViewModel
import com.example.login.data.modelsAPI.DailyForecastAPI
import com.example.login.data.repository.ForecastRepository
import com.example.login.ui.mainScreens.cities.adapter.RecyclerClick
import kotlinx.coroutines.launch

class CitiesViewModel(
    private val repo: ForecastRepository
) : BaseViewModel(), RecyclerClick {
    val addCityTrigger: MutableLiveData<Boolean> = MutableLiveData()
    val showCityInfoTrigger: MutableLiveData<Boolean> = MutableLiveData()
    val newCityToAdd: MutableLiveData<DailyForecastAPI> = MutableLiveData()
    var cities: MutableLiveData<MutableList<DailyForecastAPI>> = MutableLiveData()
    var citiesRepoList: MutableLiveData<MutableList<String>> = MutableLiveData()

    init {
        fetchData()
        citiesRepoList.value = repo.cityList
    }


    fun fetchData() {
        viewModelScope.launch {
            cities.value = repo.getForecastData()
        }
    }

    fun onAddCity() {
        addCityTrigger.value = true
    }

    override fun showCityInfo(item: DailyForecastAPI) {
        repo.getCityInfo(item)
        newCityToAdd.value = item
        showCityInfoTrigger.value = true
    }
}