package com.example.login.data.localeModels


data class CityLocale(
    val coordLocale: CoordLocale,
    val country: String,
    val id: Int,
    val name: String,
    val population: Int,
    val timezone: Int
)
