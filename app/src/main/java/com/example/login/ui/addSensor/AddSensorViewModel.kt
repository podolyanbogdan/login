package com.example.login.ui.addSensor

import androidx.lifecycle.MutableLiveData
import com.example.login.arch.BaseViewModel
import com.example.login.constants.Constants.EMPTY_FIELD
import com.example.login.data.House
import com.example.login.data.LocaleSensor
import com.example.login.repository.SensorRepository
import kotlin.random.Random

class AddSensorViewModel(private val repo: SensorRepository) : BaseViewModel() {
    val createSensorTrigger: MutableLiveData<Boolean> = MutableLiveData()
    val roomSetError: MutableLiveData<String> = MutableLiveData()

    val localeSensor = LocaleSensor()
    fun fetchRoom(roomValue: CharSequence) {
        localeSensor.room.set(roomValue.toString())
    }

    fun createLocaleSensor() {
        if (!localeSensor.room.get().isNullOrEmpty()) {
            repo.addNewSensorsList(
                House(
                    room = localeSensor.room.get() ?: "",
                    type = localeSensor.type,
                    subtype = localeSensor.subtype,
                    value = repo.getRandomValue()
                )
            )
            createSensorTrigger.value = true
        } else {
            roomSetError.value = EMPTY_FIELD
        }
    }
}