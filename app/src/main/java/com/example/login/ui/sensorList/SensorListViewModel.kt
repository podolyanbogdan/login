package com.example.login.ui.sensorList

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope

import com.example.login.arch.BaseViewModel
import com.example.login.data.House
import com.example.login.repository.SensorRepository
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch

class SensorListViewModel(private val repo: SensorRepository) : BaseViewModel() {
    val sensors: MutableLiveData<List<House>> = repo.sensors
    val addSensorTrigger = MutableLiveData<Boolean>()
    val hidePrompt = MutableLiveData<Boolean>()
    val showLoadingGif = MutableLiveData<Boolean>()

    init {
        hidePrompt.value = !sensors.value.isNullOrEmpty()
    }

    private fun anyProperties() {
        hidePrompt.value = true
        showLoadingGif.value = sensors.value.isNullOrEmpty()
    }

    //parse html and return json to repo
    private fun fetchSensorList() {
        viewModelScope.launch(IO) {
            repo.getJsonHouseString(repo.parseWebPage())
        }
        repo.syncSensors()
        anyProperties()
    }

    fun syncSensors() {
        fetchSensorList()
    }

    fun addSensor() {
        addSensorTrigger.value = true
    }

}