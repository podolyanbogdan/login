package com.example.login.data.modelsAPI


import com.google.gson.annotations.SerializedName

data class CityAPI(
    @SerializedName("coord")
    val coordAPI: CoordAPI,
    @SerializedName("country")
    val country: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("population")
    val population: Int,
    @SerializedName("timezone")
    val timezone: Int
)