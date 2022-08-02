package com.example.login.ui.addSensor

import androidx.lifecycle.MutableLiveData
import com.example.login.arch.BaseViewModel
import com.example.login.data.House
import com.example.login.data.LocaleSensor
import com.example.login.repository.SensorRepository
import kotlin.random.Random

class AddSensorViewModel(private val repo: SensorRepository) : BaseViewModel() {
    val createSensorTrigger: MutableLiveData<Boolean> = MutableLiveData()
    private val localeSensor = LocaleSensor()

    fun fetchRoom(roomValue: CharSequence) {
        localeSensor.room.set(roomValue.toString())
    }

    fun fetchSubtype(subtypeValue: CharSequence) {
        localeSensor.subtype.set(subtypeValue.toString())
    }

    fun fetchType(typeValue: CharSequence) {
        localeSensor.type.set(typeValue.toString())
    }

    fun fetchValue(valueValue: CharSequence) {
        localeSensor.value.set(valueValue.toString())
    }

    fun createLocaleSensor() {
        repo.addNewSensorsList(
            House(
                room = localeSensor.room.get() ?: "",
                subtype = localeSensor.subtype.get() ?: "",
                type = localeSensor.type.get() ?: "",
                value = localeSensor.value.get() ?: "",
            )
        )
        createSensorTrigger.value = true
    }
}