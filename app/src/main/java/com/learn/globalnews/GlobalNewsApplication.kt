package com.learn.globalnews

import android.app.Application
import com.learn.globalnews.di.*
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class GlobalNewsApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin{
            androidLogger(if (BuildConfig.DEBUG) Level.ERROR else Level.NONE)
            androidContext(this@GlobalNewsApplication)
            modules(listOf(
                newsListViewModelModule, topNewsFragmentViewModelModule,
                repositoryModule, apiModule,
                retrofitModule
            ))
        }
    }
}