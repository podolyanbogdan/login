package com.example.login.di

import com.example.login.repo.AgeRepository
import com.example.login.repo.AgeRepositoryImpl
import org.koin.dsl.module

val repositoryModule = module {
    factory<AgeRepository> { AgeRepositoryImpl(get()) }
}