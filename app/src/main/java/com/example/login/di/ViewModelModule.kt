package com.example.login.di

import com.example.login.ui.appbarActivity.BottomAppBarViewModel
import com.example.login.ui.introducingFragments.first.FirstIntroduceViewModel
import com.example.login.ui.introducingFragments.parent.ParentPageViewModel
import com.example.login.ui.introducingFragments.second.SecondIntroduceViewModel
import com.example.login.ui.mainScreens.cities.CitiesViewModel
import com.example.login.ui.introducingFragments.thirth.ThirdIntroduceViewModel
import com.example.login.ui.mainScreens.home.HomeViewModel
import com.example.login.ui.mainScreen.MainViewModel
import com.example.login.ui.mainScreens.addLocation.AddLocationViewModel
import com.example.login.ui.mainScreens.permissionScreen.PermissionLocaleViewModel
import com.example.login.ui.mainScreens.settings.SettingsViewModel
import com.example.login.ui.mainScreens.weekForecast.WeekForecastViewModel
import com.example.login.ui.splash.SplashViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { MainViewModel() }
    viewModel { SplashViewModel(get()) }
    viewModel { CitiesViewModel(get()) }
    viewModel { HomeViewModel(get()) }
    viewModel { SettingsViewModel() }
    viewModel { BottomAppBarViewModel() }
    viewModel { FirstIntroduceViewModel() }
    viewModel { SecondIntroduceViewModel() }
    viewModel { ThirdIntroduceViewModel(get()) }
    viewModel { ParentPageViewModel() }
    viewModel { AddLocationViewModel(get()) }
    viewModel { PermissionLocaleViewModel(get()) }
    viewModel { WeekForecastViewModel(get()) }

}