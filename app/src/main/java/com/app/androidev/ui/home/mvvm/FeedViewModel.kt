package com.app.androidev.ui.home.mvvm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.androidev.app.model.favorite.ResponseFavorite
import com.app.androidev.app.model.feeds.FeedItem
import com.app.androidev.app.usecase.GetFavoriteUseCase
import com.app.androidev.app.usecase.SetFavoriteUseCase
import com.app.androidev.app.usecase.GetFeedUseCase
import com.app.androidev.app.usecase.SaveFavoriteUseCase
import com.app.androidev.utils.base.DataState
import com.app.androidev.utils.base.Event
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FeedViewModel @Inject constructor(
    private val getFeedUseCase: GetFeedUseCase,
    private val setFavoriteUseCase: SetFavoriteUseCase,
    private val saveFavoriteUseCase: SaveFavoriteUseCase,
    private val getFavoriteUseCase: GetFavoriteUseCase
) : ViewModel() {

    private val _feedUiState = MutableLiveData<Event<FeedUiState>>()
    val feedUiState: LiveData<Event<FeedUiState>> get() = _feedUiState

    private val _favoriteUiState = MutableLiveData<Event<FavoriteUiState>>()
    val favoriteUiState: LiveData<Event<FavoriteUiState>> get() = _favoriteUiState

    val favoriteList =
        MutableLiveData<Event<List<com.app.androidev.app.data.db.entities.FeedItem>>>()

    fun getFeeds() =
        viewModelScope.launch {
            getFeedUseCase().collect { data ->
                when (data) {
                    is DataState.Loading -> {
                        _feedUiState.value = Event(FeedUiState.ShowLoading)
                    }
                    is DataState.success -> {
                        _feedUiState.value = Event(
                            FeedUiState.Success(data.data)
                        )
                    }
                    is DataState.Error -> {
                        data.exception.printStackTrace()
                        _feedUiState.value = Event(
                            FeedUiState.Error(data.exception.message.orEmpty())
                        )
                    }
                    is DataState.otherError -> {
                        _feedUiState.value = Event(FeedUiState.Error(data.error))
                    }
                }
            }
        }

    fun setFavorite(id: Int?) =
        viewModelScope.launch {
            setFavoriteUseCase(id!!).collect { data ->
                when (data) {
                    is DataState.Loading -> {
                        _favoriteUiState.value = Event(FavoriteUiState.ShowLoading)
                    }
                    is DataState.success -> {
                        _favoriteUiState.value = Event(
                            FavoriteUiState.Success(data.data)
                        )
                    }
                    is DataState.Error -> {
                        data.exception.printStackTrace()
                        _favoriteUiState.value = Event(
                            FavoriteUiState.Error(data.exception.message.orEmpty())
                        )
                    }
                    is DataState.otherError -> {
                        _favoriteUiState.value = Event(FavoriteUiState.Error(data.error))
                    }
                }
            }
        }

    fun getFavoriteList() {
        viewModelScope.launch {
            favoriteList.postValue(Event(getFavoriteUseCase()))
        }
    }

    fun saveFavorite(feed: FeedItem) {
        viewModelScope.launch {
            saveFavoriteUseCase(
                com.app.androidev.app.data.db.entities.FeedItem(
                    date = feed.date!!,
                    image = feed.image!!,
                    description = feed.description!!,
                    published = "",
                    title = feed.title!!,
                    authorId = "",
                    link = ""
                )
            )
        }
    }

    sealed class FeedUiState {
        object ShowLoading : FeedUiState()
        class Success(val result: List<FeedItem>?) : FeedUiState()
        class Error(val msg: String) : FeedUiState()
    }

    sealed class FavoriteUiState {
        object ShowLoading : FavoriteUiState()
        class Success(val result: ResponseFavorite) : FavoriteUiState()
        class Error(val msg: String) : FavoriteUiState()
    }
}