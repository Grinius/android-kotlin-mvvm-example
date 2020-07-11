package com.oxygen.freecorona.app

import android.app.Application
import com.oxygen.freecorona.module.interactorModule
import com.oxygen.freecorona.module.networkModule
import com.oxygen.freecorona.module.repositoryModule
import com.oxygen.freecorona.module.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MainApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            printLogger()
            androidContext(this@MainApplication)
            modules(
                listOf(
                    networkModule,
                    viewModelModule,
                    repositoryModule,
                    interactorModule
                )
            )
        }
    }
}

/*
Koin - DI
RxJava - async
Retrofit
Design pattern - MVVM
 */