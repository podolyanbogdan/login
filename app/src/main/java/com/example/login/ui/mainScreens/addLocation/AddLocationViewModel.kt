package com.example.login.ui.mainScreens.addLocation

import androidx.lifecycle.MutableLiveData
import com.example.login.arch.BaseViewModel
import com.example.login.data.repository.ForecastRepository

class AddLocationViewModel(
    private val repo: ForecastRepository
) : BaseViewModel() {
    val newCityName: MutableLiveData<String> = MutableLiveData()
    val addCityTrigger: MutableLiveData<Boolean> = MutableLiveData()

    fun onAddCity() {
        repo.addNewCity(newCityName.value ?: "")
        addCityTrigger.value = true
    }
}