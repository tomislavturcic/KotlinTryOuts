package com.tt.kotlintryout.data.network

import com.tt.kotlintryout.BuildConfig
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitServiceBuilder {

    private const val BASE_URL = "https://demo.martian.agency/api/"
    private const val X_AUTH_TOKEN = "bWFydGlhbmFuZG1hY2hpbmU="

    fun create() : PostsServiceApi {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(createOkHttpClient())
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()

        return retrofit.create(PostsServiceApi::class.java)
    }

    private fun createOkHttpClient() : OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(createAuthInterceptor())
            .addInterceptor(createLoggingInterceptor())
            .build()
    }

    private fun createLoggingInterceptor() : Interceptor {
        val interceptor = HttpLoggingInterceptor()
        var logLevel = HttpLoggingInterceptor.Level.NONE
        if(BuildConfig.DEBUG){
            logLevel = HttpLoggingInterceptor.Level.BODY
        }
        interceptor.level = logLevel
        return interceptor
    }

    private fun createAuthInterceptor() : Interceptor {
        return object : Interceptor {
            override fun intercept(chain: Interceptor.Chain): Response {
                val request = chain.request()

                val headerRequest = request.newBuilder()
                    .addHeader("X-Auth", X_AUTH_TOKEN)
                    .build()

                return chain.proceed(headerRequest)
            }

        }
    }

}