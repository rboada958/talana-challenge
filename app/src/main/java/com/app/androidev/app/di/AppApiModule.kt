package com.app.androidev.app.di

import com.app.androidev.app.networks.api.FeedApi
import com.app.androidev.app.networks.api.LoginApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit

@Module(includes = [RetrofitModule::class])
@InstallIn(SingletonComponent::class)
class AppApiModule {

    @Provides
    fun provideLoginApi(retrofit: Retrofit) : LoginApi = retrofit.create(LoginApi::class.java)

    @Provides
    fun provideFeedApi(retrofit: Retrofit) : FeedApi = retrofit.create(FeedApi::class.java)
}