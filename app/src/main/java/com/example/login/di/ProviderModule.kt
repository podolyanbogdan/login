package com.example.login.di


import com.example.login.retrofit.RetrofitInstance
import com.example.login.retrofit.api.AgeApi
import org.koin.dsl.module

val providerModule = module {
    single { RetrofitInstance() }
    single { get<RetrofitInstance>().createService(AgeApi::class.java) }
}