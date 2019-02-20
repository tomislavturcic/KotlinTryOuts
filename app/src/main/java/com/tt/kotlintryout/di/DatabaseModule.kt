package com.tt.kotlintryout.di

import com.tt.kotlintryout.data.local.AppDatabase
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module.module

val databaseModule = module {

    single { AppDatabase.create(androidApplication()) }

}