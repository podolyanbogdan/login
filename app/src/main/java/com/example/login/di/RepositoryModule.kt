package com.example.login.di

import com.example.login.repository.BWStorage
import com.example.login.repository.ImageRepository
import org.koin.dsl.module

val repositoryModule = module {
    single { ImageRepository() }
    single { BWStorage() }
}