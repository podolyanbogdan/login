package com.example.login.di

import com.example.login.ui.gamesViewModel.*
import com.example.login.ui.main.MainViewModel
import com.example.login.ui.menu.MenuViewModel
import com.example.login.ui.settings.SettingsViewModel
import com.example.login.ui.splash.SplashViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { MainViewModel() }
    viewModel { SplashViewModel() }
    viewModel { MenuViewModel() }
    viewModel { SceneLectureHallViewModel() }
    viewModel { SceneLaterBadEndingViewModel() }
    viewModel { SceneRightAwayModel() }
    viewModel { SceneGameViewModel() }
    viewModel { SceneBookViewModel() }
    viewModel { SceneMarryViewModel() }
    viewModel { SettingsViewModel() }
}