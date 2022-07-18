package com.example.login.di

import com.example.login.ui.age.AgeViewModel
import com.example.login.ui.character.CharacterViewModel
import com.example.login.ui.level.LevelViewModel
import com.example.login.ui.main.MainViewModel
import com.example.login.ui.map.MapViewModel
import com.example.login.ui.points.PointsViewModel
import com.example.login.ui.settings.SettingsViewModel
import com.example.login.ui.splash.SplashViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { MainViewModel() }
    viewModel { SplashViewModel() }
    viewModel { AgeViewModel() }
    viewModel { LevelViewModel(get()) }
    viewModel { CharacterViewModel() }
    viewModel { MapViewModel() }
    viewModel { PointsViewModel(get()) }
    viewModel { SettingsViewModel(get()) }
}