package com.aripratom.gamecatalogue

import android.app.Application
import com.aripratom.core.di.databaseModule
import com.aripratom.core.di.networkModule
import com.aripratom.core.di.repositoryModule
import com.aripratom.gamecatalogue.di.useCaseModule
import com.aripratom.gamecatalogue.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class MyApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger(Level.ERROR)
            androidContext(this@MyApplication)
            modules(
                    listOf(
                            databaseModule,
                            networkModule,
                            repositoryModule,
                            useCaseModule,
                            viewModelModule
                    )
            )
        }
    }
}