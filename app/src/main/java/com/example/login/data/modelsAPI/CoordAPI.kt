package com.example.login.data.modelsAPI


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CoordAPI(
    @SerialName("lat")
    val lat: Double,
    @SerialName("lon")
    val lon: Double
)