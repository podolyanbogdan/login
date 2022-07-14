package com.example.login.di

import com.example.login.ui.dialogFragment.TagDialogViewModel
import com.example.login.ui.main.MainViewModel
import com.example.login.ui.screens.addTask.AddTaskViewModel
import com.example.login.ui.screens.mainScreen.MainScreenViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { MainViewModel() }
    viewModel { MainScreenViewModel(get()) }
    viewModel { AddTaskViewModel() }
    viewModel { TagDialogViewModel() }
}