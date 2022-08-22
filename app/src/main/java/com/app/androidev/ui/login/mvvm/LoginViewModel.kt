package com.app.androidev.ui.login.mvvm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.androidev.app.model.login.RequestLogin
import com.app.androidev.app.model.login.ResponseLogin
import com.app.androidev.app.usecase.ApiTokenUseCase
import com.app.androidev.app.usecase.LoginUseCase
import com.app.androidev.utils.base.DataState
import com.app.androidev.utils.base.Event
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    val loginUseCase: LoginUseCase,
    val apiTokenUseCase: ApiTokenUseCase
) : ViewModel() {

    private val _loginUiState = MutableLiveData<Event<LoginUiState>>()
    val loginUiState: LiveData<Event<LoginUiState>> get() = _loginUiState

    fun login(requestLogin: RequestLogin) =
        viewModelScope.launch {
            loginUseCase(requestLogin).collect { data ->
                when (data) {
                    is DataState.Loading -> {
                        _loginUiState.value = Event(LoginUiState.ShowLoading)
                    }
                    is DataState.success -> {
                        _loginUiState.value = Event(LoginUiState.Success(data.data))
                    }
                    is DataState.Error -> {
                        data.exception.printStackTrace()
                        _loginUiState.value =
                            Event(LoginUiState.Error(data.exception.message.orEmpty()))
                    }
                    is DataState.otherError -> {
                        _loginUiState.value = Event(LoginUiState.Error(data.error))
                    }
                }
            }
        }

    fun setApiToken(apiToken: String?) {
        viewModelScope.launch {
            apiTokenUseCase(apiToken!!).collectLatest {}
        }
    }

    sealed class LoginUiState {
        object ShowLoading : LoginUiState()
        class Success(val result: ResponseLogin) : LoginUiState()
        class Error(val msg: String) : LoginUiState()
    }

}