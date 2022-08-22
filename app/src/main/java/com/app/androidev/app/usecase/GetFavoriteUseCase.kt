package com.app.androidev.app.usecase

import com.app.androidev.ui.home.mvvm.FeedRepository
import javax.inject.Inject

class GetFavoriteUseCase @Inject constructor(private val repository: FeedRepository) {

    suspend operator fun invoke() = repository.favoriteList()
}