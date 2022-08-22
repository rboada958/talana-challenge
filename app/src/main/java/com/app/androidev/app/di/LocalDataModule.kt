package com.app.androidev.app.di

import android.content.Context
import com.app.androidev.app.data.LocalDataSource
import com.app.androidev.app.data.LocalDataStore
import com.app.androidev.app.data.db.TalanaDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class LocalDataModule {

    @Provides
    @Singleton
    fun provideLocalDataStore(@ApplicationContext context:  Context) = LocalDataStore(context)

    @Provides
    @Singleton
    fun provideLocalDataSource(
        database: TalanaDatabase,
        localDataStore: LocalDataStore
    ) = LocalDataSource(localDataStore, database)

}