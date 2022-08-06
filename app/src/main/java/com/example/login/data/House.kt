package com.example.login.data


import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class House(
    var id: Int = 0,
    @SerializedName("room")
    var room: String,
    @SerializedName("subtype")
    val subtype: String,
    @SerializedName("type")
    val type: String,
    @SerializedName("value")
    var value: String,
)