package com.example.login.ui.wifi

import android.net.wifi.ScanResult

data class WifiModel(
    val scanResult: ScanResult,
    val name: String = "",
    val power: Int = 0,
    val description: String = ""
)