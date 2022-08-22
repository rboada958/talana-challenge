package com.app.androidev.app.data

import com.app.androidev.app.data.db.TalanaDatabase
import com.app.androidev.app.data.db.entities.FeedItem
import javax.inject.Inject

class LocalDataSource @Inject constructor(
    private val localDataStore: LocalDataStore,
    private val database: TalanaDatabase
) {

    suspend fun addFeedFavorite(feedItem: FeedItem): Boolean {
        return try {
            database.feedFavoriteDao().insert(feedItem)
            true
        } catch (e: Exception) {
            e.printStackTrace()
            false
        }
    }

    suspend fun getFavoriteList(): MutableList<FeedItem> {
        return database.feedFavoriteDao().getFavoriteList()
    }
}