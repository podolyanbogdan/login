package com.example.login.di

import com.example.login.ui.age.AgeViewModel
import com.example.login.ui.main.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { MainViewModel() }
    viewModel { AgeViewModel(get()) }
}