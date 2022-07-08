package com.example.login.di

import com.example.login.repository.PreferenceStorage
import com.example.login.repository.ScenarioRepository
import org.koin.dsl.module

val repositoryModule = module {
    single { PreferenceStorage(get()) }
    single { ScenarioRepository(get()) }
}