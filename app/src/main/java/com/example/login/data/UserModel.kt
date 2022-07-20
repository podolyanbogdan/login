package com.example.login.data

import com.google.android.gms.maps.model.LatLng


data class UserModel(
    val location: String = "",
    val level: String = "",
    val character: String = "",
    val availableDate: String = "",
    val availableTime: String = ""
)