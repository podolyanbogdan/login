package com.example.login.di

import com.example.login.retrofit.NetworkFactory
import com.example.login.retrofit.api.NetworkApi
import com.example.login.retrofit.networkLoader.NetworkLoader
import org.koin.dsl.module

val networkModule = module {
    single { NetworkLoader(get()) }
    single { NetworkFactory() }
    single { get<NetworkFactory>().createService(NetworkApi::class.java) }
}