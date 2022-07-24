package com.example.login.di

import com.example.login.ui.addNote.AddNoteViewModel
import com.example.login.ui.emergency.EmergencyViewModel
import com.example.login.ui.help.HelpViewModel
import com.example.login.ui.main.MainViewModel
import com.example.login.ui.notesList.NotesListViewModel
import com.example.login.ui.splash.SplashViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { MainViewModel() }
    viewModel { SplashViewModel() }
    viewModel { NotesListViewModel(get()) }
    viewModel { EmergencyViewModel(get()) }
    viewModel { HelpViewModel() }
    viewModel { AddNoteViewModel(get()) }

}