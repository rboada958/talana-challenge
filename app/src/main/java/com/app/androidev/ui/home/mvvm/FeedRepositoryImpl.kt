package com.app.androidev.ui.home.mvvm

import com.app.androidev.app.data.LocalDataSource
import com.app.androidev.app.model.favorite.RequestFavorite
import com.app.androidev.app.model.favorite.ResponseFavorite
import com.app.androidev.app.model.feeds.FeedItem
import com.app.androidev.app.networks.api.FeedApi
import com.app.androidev.utils.base.DataState
import com.app.androidev.utils.runRemoteApiCall
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class FeedRepositoryImpl @Inject constructor(
    private val localDataSource: LocalDataSource,
    private val api: FeedApi
) : FeedRepository {

    override suspend fun feed(): Flow<DataState<List<FeedItem>?>> =
        flow {
            emit(DataState.Loading)
            runRemoteApiCall(
                success = {
                    emit(DataState.success(data = data))
                }
            ) {
                api.feed()
            }
        }

    override suspend fun favorite(id: Int): Flow<DataState<ResponseFavorite>> =
        flow {
            emit(DataState.Loading)
            runRemoteApiCall(
                success = {
                    emit(DataState.success(data = data))
                }
            ) {
                api.favorite(RequestFavorite(postid = id.toString()))
            }
        }

    override suspend fun saveFavorite(feedItem: com.app.androidev.app.data.db.entities.FeedItem) {
        localDataSource.addFeedFavorite(feedItem)
    }

    override suspend fun favoriteList(): MutableList<com.app.androidev.app.data.db.entities.FeedItem> {
        return localDataSource.getFavoriteList()
    }

}