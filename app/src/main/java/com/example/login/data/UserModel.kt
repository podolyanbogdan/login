package com.example.login.data

import androidx.annotation.DrawableRes
import com.google.android.gms.maps.model.LatLng

data class UserModel(
    val location: LatLng,
    val level: String = "",
    val character: String? = "",
    @DrawableRes
    val characterImage: Int = 0,
    val availableDate: String = "",
    val availableTime: String = ""
)