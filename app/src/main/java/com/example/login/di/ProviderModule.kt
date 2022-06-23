package com.example.login.di

import com.example.login.ui.wifi.WifiItemManager
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

val providerModule = module {
    single { WifiItemManager(context = androidApplication()) }
}