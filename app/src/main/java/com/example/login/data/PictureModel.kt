package com.example.login.data


import com.google.gson.annotations.SerializedName

data class PictureModel(
    @SerializedName("full")
    val full: String = "",
    @SerializedName("large")
    val large: String = "",
    @SerializedName("med")
    val med: String = "",
    @SerializedName("small")
    val small: String = ""
)