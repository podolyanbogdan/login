package com.example.login.ui.addSensor

import androidx.lifecycle.MutableLiveData
import com.example.login.arch.BaseViewModel
import com.example.login.data.House
import com.example.login.repository.SensorRepository

class AddSensorViewModel(private val repo: SensorRepository) : BaseViewModel() {
    val createSensorTrigger: MutableLiveData<Boolean> = MutableLiveData()

    val room: MutableLiveData<String> = MutableLiveData()
    val type: MutableLiveData<String> = MutableLiveData()
    var subtype: MutableLiveData<String> = MutableLiveData()
    var valueText: MutableLiveData<String> = MutableLiveData()


    fun createSensor() {
        repo.addNewSensorsList(
            House(
                room = room.value ?: "",
                type = type.value ?: "",
                subtype = subtype.value ?: "",
                value = valueText.value ?: "",
            )
        )
        createSensorTrigger.value = true
    }
}