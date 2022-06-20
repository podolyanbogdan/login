package com.example.login.ui.another

import android.app.Application
import com.example.login.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class InternshipApp : Application() {

    private val appModules by lazy {
        listOf(viewModelModule)
    }

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@InternshipApp)
            modules(appModules)
        }
    }

}