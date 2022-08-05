package com.example.login.ui.sensorList

import com.example.login.data.House

interface SensorClickListener {
    fun deleteSensor(sensor: House)
}