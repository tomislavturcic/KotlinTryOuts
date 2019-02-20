package com.tt.kotlintryout

import android.app.Application
import com.tt.kotlintryout.di.databaseModule
import com.tt.kotlintryout.di.viewModelsModule
import com.tt.kotlintryout.di.networkModule
import com.tt.kotlintryout.di.repositoryModule
import com.tt.kotlintryout.utils.DebugLogger
import com.tt.kotlintryout.utils.LogUtils
import org.koin.android.ext.android.startKoin

class App : Application() {

    override fun onCreate() {
        super.onCreate()

        if (BuildConfig.DEBUG) {
            LogUtils.setLogger(DebugLogger())
        }

        startKoin(
            this, listOf(
                networkModule,
                repositoryModule,
                viewModelsModule,
                databaseModule
            )
        )
    }
}