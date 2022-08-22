package com.app.androidev.app.usecase

import com.app.androidev.ui.home.mvvm.FeedRepository
import javax.inject.Inject

class SetFavoriteUseCase @Inject constructor(private val repository: FeedRepository) {

    suspend operator fun invoke(id: Int) =
        repository.favorite(id)
}