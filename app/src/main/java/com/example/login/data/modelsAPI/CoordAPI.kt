package com.example.login.data.modelsAPI


import com.google.gson.annotations.SerializedName

data class CoordAPI(
    @SerializedName("lat")
    val lat: Double,
    @SerializedName("lon")
    val lon: Double
)