package com.example.login.di

import com.example.login.viewmodels.GameModeViewModel
import com.example.login.viewmodels.GameViewModel
import com.example.login.viewmodels.SplashViewModel
import com.example.login.viewmodels.TurnsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { SplashViewModel() }
    viewModel { GameViewModel() }
    viewModel { GameModeViewModel() }
    viewModel { TurnsViewModel() }
}