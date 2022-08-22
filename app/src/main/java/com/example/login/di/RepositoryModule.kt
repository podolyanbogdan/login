package com.example.login.di

import com.example.login.data.repository.BirdRepository
import org.koin.dsl.module

val repositoryModule = module {
    single { BirdRepository() }
}