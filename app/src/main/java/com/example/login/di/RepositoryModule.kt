package com.example.login.di

import com.example.login.repository.SensorRepository
import org.koin.dsl.module

val repositoryModule = module {
    single { SensorRepository() }
}