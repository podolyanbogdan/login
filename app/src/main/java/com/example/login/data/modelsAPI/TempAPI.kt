package com.example.login.data.modelsAPI


import com.google.gson.annotations.SerializedName
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class TempAPI(
    @SerialName("day")
    val day: Double,
    @SerialName("eve")
    val eve: Double,
    @SerialName("max")
    val max: Double,
    @SerialName("min")
    val min: Double,
    @SerialName("morn")
    val morn: Double,
    @SerialName("night")
    val night: Double
)