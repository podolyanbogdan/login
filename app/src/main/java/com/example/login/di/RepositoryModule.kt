package com.example.login.di

import com.example.login.repository.MyRepository
import com.example.login.repository.PreferenceStorage
import org.koin.dsl.module

val repositoryModule = module {
    single { PreferenceStorage(get()) }
    single { MyRepository(get()) }
}