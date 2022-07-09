package com.example.login.di

import com.example.login.ui.gamesScreens.badEndingScene.SceneLaterBadEndingViewModel
import com.example.login.ui.gamesScreens.bookScene.SceneBookViewModel
import com.example.login.ui.gamesScreens.gameScene.SceneGameViewModel
import com.example.login.ui.gamesScreens.lectureHallScene.SceneLectureHallViewModel
import com.example.login.ui.gamesScreens.marryScene.SceneMarryViewModel
import com.example.login.ui.gamesScreens.rigthAwayScene.SceneRightAwayModel
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
    viewModel { SceneLectureHallViewModel(get()) }
    viewModel { SceneLaterBadEndingViewModel(get()) }
    viewModel { SceneRightAwayModel(get()) }
    viewModel { SceneGameViewModel(get()) }
    viewModel { SceneBookViewModel(get()) }
    viewModel { SceneMarryViewModel(get()) }
    viewModel { SettingsViewModel() }
}