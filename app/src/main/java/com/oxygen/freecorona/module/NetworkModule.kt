package com.oxygen.freecorona.module

import com.google.gson.GsonBuilder
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import com.oxygen.freecorona.network.FreeCoronaApiService
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

private const val READ_TIMEOUT_S = 30L
private const val CONNECT_TIMEOUT_S = 30L
private const val WRITE_TIMEOUT_S = 30L
private const val BASE_SERVICES_URL = "https://FreeCoronaApiService.com/api/"

val networkModule = module {

    single { GsonBuilder().create() }

    single {
        OkHttpClient.Builder().apply {
            readTimeout(READ_TIMEOUT_S, TimeUnit.SECONDS)
            connectTimeout(CONNECT_TIMEOUT_S, TimeUnit.SECONDS)
            writeTimeout(WRITE_TIMEOUT_S, TimeUnit.SECONDS)
            addInterceptor(HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            })
            retryOnConnectionFailure(true)
        }.build()
    }

    single {
        Retrofit.Builder()
            .baseUrl(BASE_SERVICES_URL)
            .addConverterFactory(GsonConverterFactory.create(get()))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(get())
            .build()
    }

    single { provideMhsApiService(get()) }
}

fun provideMhsApiService(retrofit: Retrofit): FreeCoronaApiService =
    retrofit.create(FreeCoronaApiService::class.java)