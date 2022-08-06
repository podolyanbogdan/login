package com.example.login.di

import com.example.login.ui.addSensor.AddSensorViewModel
import com.example.login.ui.main.MainViewModel
import com.example.login.ui.sensorList.SensorListViewModel
import com.example.login.ui.splash.SplashViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { MainViewModel() }
    viewModel { SplashViewModel() }
    viewModel { SensorListViewModel(get()) }
    viewModel { AddSensorViewModel(get()) }
}