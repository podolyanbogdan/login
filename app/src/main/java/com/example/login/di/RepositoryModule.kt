package com.example.login.di

import com.example.login.data.repository.MemesRepository
import org.koin.dsl.module

val repositoryModule = module {
    single { MemesRepository() }
}