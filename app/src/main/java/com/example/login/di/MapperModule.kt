package com.example.login.di

import com.example.login.data.mapper.*
import org.koin.dsl.module

val mapperModule = module {
    single { CityMapper(get()) }
    single { CoordMapper() }
    single { DailyForecastMapper(get(), get()) }
    single { FeelsLikeMapper() }
    single { MainInfoMapper(get(),get(),get()) }
    single { TempMapper() }
    single { WeatherMapper() }
}