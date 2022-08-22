package com.app.androidev.ui.home.mvvm

import com.app.androidev.app.model.favorite.ResponseFavorite
import com.app.androidev.app.model.feeds.FeedItem
import com.app.androidev.utils.base.DataState
import kotlinx.coroutines.flow.Flow

interface FeedRepository {

    suspend fun feed() : Flow<DataState<List<FeedItem>?>>
    suspend fun favorite(id: Int) : Flow<DataState<ResponseFavorite>>
    suspend fun saveFavorite(feedItem: com.app.androidev.app.data.db.entities.FeedItem)
    suspend fun favoriteList() : MutableList<com.app.androidev.app.data.db.entities.FeedItem>

}