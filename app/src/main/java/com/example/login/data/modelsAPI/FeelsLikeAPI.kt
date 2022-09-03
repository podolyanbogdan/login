package com.example.login.data.modelsAPI


import com.google.gson.annotations.SerializedName
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class FeelsLikeAPI(
    @SerialName("day")
    val day: Double,
    @SerialName("eve")
    val eve: Double,
    @SerialName("morn")
    val morn: Double,
    @SerialName("night")
    val night: Double
)