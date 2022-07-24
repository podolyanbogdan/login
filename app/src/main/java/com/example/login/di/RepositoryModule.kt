package com.example.login.di

import com.example.login.repository.NoteRepository
import org.koin.dsl.module

val repositoryModule = module {
    single { NoteRepository(get()) }
}