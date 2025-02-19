package com.example

import android.app.Application
import com.example.foodist.core.di.databaseModule
import com.example.foodist.core.di.networkModule
import com.example.foodist.core.di.repositoryModule
import com.example.foodist.presentation.di.useCaseModule
import com.example.foodist.presentation.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin{
            androidLogger(level = Level.NONE)
            androidContext(this@MyApplication)
            modules(
                listOf(
                    databaseModule,
                    networkModule,
                    repositoryModule,
                    viewModelModule,
                    useCaseModule
                )
            )
        }
    }
}