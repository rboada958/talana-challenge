package com.app.androidev.app.usecase

import com.app.androidev.app.data.db.entities.FeedItem
import com.app.androidev.ui.home.mvvm.FeedRepository
import javax.inject.Inject

class SaveFavoriteUseCase @Inject constructor(private val repository: FeedRepository) {

    suspend operator fun invoke(feedItem: FeedItem) =
        repository.saveFavorite(feedItem)
}