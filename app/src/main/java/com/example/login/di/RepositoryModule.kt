package com.example.login.di

import com.example.login.data.prefs.PreferenceStorage
import com.example.login.data.repository.ForecastRepository
import com.example.login.data.repository.PrefsRepository
import org.koin.dsl.module

val repositoryModule = module {
    single { ForecastRepository(get(), get(), get()) }
    single { PreferenceStorage(get()) }
    single { PrefsRepository(get()) }
}