package com.app.androidev.ui.login.mvvm

import com.app.androidev.app.model.login.RequestLogin
import com.app.androidev.app.model.login.ResponseLogin
import com.app.androidev.utils.base.DataState
import kotlinx.coroutines.flow.Flow

interface LoginRepository {

    suspend fun login(requestLogin: RequestLogin) : Flow<DataState<ResponseLogin>>
    suspend fun apiToken(token: String) : Flow<Boolean>
}