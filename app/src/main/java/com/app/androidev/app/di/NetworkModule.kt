package com.app.androidev.app.di

import android.content.Context
import android.util.Log
import com.app.androidev.app.data.LocalDataStore
import com.app.androidev.app.networks.interceptor.ConnectivityInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import java.util.concurrent.TimeUnit

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @Provides
    fun provideInterceptor(): HttpLoggingInterceptor {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        return interceptor
    }

    @Provides
    fun provideConnectivityInterceptor(@ApplicationContext context: Context): ConnectivityInterceptor {
        return ConnectivityInterceptor(context)
    }

    @Provides
    fun provideOkHttpClient(
        localDataStore: LocalDataStore,
        loggingInterceptor: HttpLoggingInterceptor
    ): OkHttpClient {

        return OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .addInterceptor { chain ->
                val requestBuilder = chain.request().newBuilder()
                    .addHeader("apikey", localDataStore.getAccessToken())
                val request = requestBuilder.build()
                chain.proceed(request)
            }
            .readTimeout(40, TimeUnit.MINUTES)
            .connectTimeout(40, TimeUnit.MINUTES)
            .callTimeout(10, TimeUnit.MINUTES)
            .writeTimeout(10, TimeUnit.MINUTES)
            .build()
    }
}