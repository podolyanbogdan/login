package com.example.login.ui.mainScreens.addLocation

import androidx.lifecycle.MutableLiveData
import com.example.login.arch.BaseViewModel
import com.example.login.data.repository.ForecastStorage

class AddLocationViewModel(
    private val repo: ForecastStorage
) : BaseViewModel() {
    val newCityName: MutableLiveData<String> = MutableLiveData()
    val showError: MutableLiveData<Boolean> = MutableLiveData()
    val addCityTrigger: MutableLiveData<Boolean> = MutableLiveData()

    fun onAddCity() {
        if(!newCityName.value.isNullOrEmpty()){
            repo.addNewCity(newCityName.value ?: "")
            addCityTrigger.value = true
        } else {
            showError.value = true
        }
    }
}