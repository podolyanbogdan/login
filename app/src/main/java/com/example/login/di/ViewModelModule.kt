package com.example.login.di

import com.example.login.ui.main.MainViewModel
import com.example.login.ui.splash.SplashViewModel
import com.example.login.ui.wifi.WifiViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { MainViewModel() }
    viewModel { SplashViewModel() }
    viewModel { WifiViewModel(wifiItemManager = get()) }
}