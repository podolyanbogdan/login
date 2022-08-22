package com.example.login.di

import com.example.login.ui.main.MainViewModel
import com.example.login.ui.screens.advanced.AdvancedViewModel
import com.example.login.ui.screens.birdsList.BirdsListViewModel
import com.example.login.ui.screens.defaultt.DefaultViewModel
import com.example.login.ui.screens.details.DetailsViewModel
import com.example.login.ui.screens.tab.TabViewModel
import com.example.login.ui.splash.SplashViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { MainViewModel() }
    viewModel { SplashViewModel() }
    viewModel { AdvancedViewModel(get()) }
    viewModel { DefaultViewModel() }
    viewModel { BirdsListViewModel(get()) }
    viewModel { DetailsViewModel(get()) }
    viewModel { TabViewModel() }
}