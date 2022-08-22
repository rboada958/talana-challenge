package com.app.androidev.app.di

import android.content.Context
import androidx.room.Room
import com.app.androidev.app.data.db.TalanaDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {

    @Provides
    fun provideDatabase(@ApplicationContext context: Context) =
        Room.databaseBuilder(context, TalanaDatabase::class.java, "talana-database")
            .fallbackToDestructiveMigration()
            .build()

    @Provides
    fun provideFeedFavoriteDao(database: TalanaDatabase) =
        database.feedFavoriteDao()
}