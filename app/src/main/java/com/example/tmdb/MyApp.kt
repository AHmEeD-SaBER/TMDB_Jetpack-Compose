package com.example.tmdb

import android.app.Application
import com.example.tmdb.network.netWorkModule
import com.example.tmdb.viewmodels.useCaseModule
import com.example.tmdb.viewmodels.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.GlobalContext.startKoin


class MyApp : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@MyApp)
            modules(
                useCaseModule,
                viewModelModule,
                netWorkModule

            )

        }
    }
}