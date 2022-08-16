package com.example.login.di

import com.example.login.ui.alert.SettingsDialogViewModel
import com.example.login.ui.editor.EditorViewModel
import com.example.login.ui.home.HomeViewModel
import com.example.login.ui.main.MainViewModel
import com.example.login.ui.splash.SplashViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { MainViewModel() }
    viewModel { SplashViewModel() }
    viewModel { HomeViewModel() }
    viewModel { EditorViewModel(get(), get()) }
    viewModel { SettingsDialogViewModel() }
}