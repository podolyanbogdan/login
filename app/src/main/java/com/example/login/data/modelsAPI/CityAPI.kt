package com.example.login.data.modelsAPI


import com.google.gson.annotations.SerializedName
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CityAPI(
    @SerialName("coord")
    val coordAPI: CoordAPI,
    @SerialName("country")
    val country: String,
    @SerialName("id")
    val id: Int,
    @SerialName("name")
    val name: String,
    @SerialName("population")
    val population: Int,
    @SerialName("timezone")
    val timezone: Int
)