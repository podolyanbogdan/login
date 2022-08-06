package com.example.login.data


import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class HouseInfo(
    @SerializedName("house")
    val house: List<House>,
    @SerializedName("name")
    val name: String,
    @SerializedName("version")
    val version: String
)