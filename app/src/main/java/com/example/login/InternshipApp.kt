package com.example.login

import android.app.Application
import com.example.login.di.*
import com.yariksoffice.lingver.Lingver
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class InternshipApp : Application() {

    private val appModules by lazy{
        listOf(viewModelModule, repositoryModule, providerModule, networkModule, mapperModule)
    }

    override fun onCreate() {
        super.onCreate()
        Lingver.init(this)
        startKoin {
            androidContext(this@InternshipApp)
            modules(appModules)
        }
    }

}
