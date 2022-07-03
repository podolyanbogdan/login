package com.example.login.di

import com.example.login.ui.main.MainViewModel
import com.example.login.ui.splash.SplashViewModel
import com.example.login.ui.translated.TranslatedWordViewModel
import com.example.login.ui.translator.TranslatorViewModel
import org.koin.android.ext.koin.androidApplication
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { MainViewModel() }
    viewModel { SplashViewModel() }
    viewModel { TranslatorViewModel(androidApplication()) }
    viewModel { TranslatedWordViewModel() }
}