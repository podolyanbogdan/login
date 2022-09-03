package com.example.login.data.localeModels

import androidx.databinding.ObservableField

data class HomeModel(
    var currentDay: ObservableField<String> = ObservableField(),
    var currentTime:ObservableField<String> = ObservableField(),
    var cityName: ObservableField<String> = ObservableField(),
    var countryName: ObservableField<String> = ObservableField(),
    var locationLon: ObservableField<Double> = ObservableField(),
    var locationLat: ObservableField<Double> = ObservableField(),
    var degrees: ObservableField<Double> = ObservableField(),
    var weatherImg:ObservableField<String> = ObservableField(),
    var humidity: ObservableField<Int> = ObservableField(),
    var windSpeed: ObservableField<Double> = ObservableField(),
)