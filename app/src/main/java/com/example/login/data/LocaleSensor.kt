package com.example.login.data

import androidx.databinding.ObservableField

data class LocaleSensor(
    val room: ObservableField<String> = ObservableField(),
    var type: String = "Sensor",
    var subtype: String = "Switch"
)

