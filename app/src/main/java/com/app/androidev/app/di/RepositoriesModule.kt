package com.app.androidev.app.di

import com.app.androidev.app.data.LocalDataSource
import com.app.androidev.app.data.LocalDataStore
import com.app.androidev.app.networks.api.FeedApi
import com.app.androidev.app.networks.api.LoginApi
import com.app.androidev.ui.home.mvvm.FeedRepository
import com.app.androidev.ui.home.mvvm.FeedRepositoryImpl
import com.app.androidev.ui.login.mvvm.LoginRepository
import com.app.androidev.ui.login.mvvm.LoginRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class RepositoriesModule {

    @Provides
    fun providesFeedRepository(localDataSource: LocalDataSource, api: FeedApi) : FeedRepository =
        FeedRepositoryImpl(localDataSource, api)

    @Provides
    fun providesLoginRepository(localDataStore: LocalDataStore, api: LoginApi) : LoginRepository =
        LoginRepositoryImpl(localDataStore, api)
}