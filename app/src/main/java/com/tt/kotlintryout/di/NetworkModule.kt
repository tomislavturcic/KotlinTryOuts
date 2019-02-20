package com.tt.kotlintryout.di

import com.tt.kotlintryout.data.network.RetrofitServiceBuilder
import org.koin.dsl.module.module

val networkModule = module {

    single { RetrofitServiceBuilder.create() }

}