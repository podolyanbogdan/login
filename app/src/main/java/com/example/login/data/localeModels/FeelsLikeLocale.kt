package com.example.login.data.localeModels

import com.google.gson.annotations.SerializedName

data class FeelsLikeLocale(
    val day: Double,
    val eve: Double,
    val morn: Double,
    val night: Double
)