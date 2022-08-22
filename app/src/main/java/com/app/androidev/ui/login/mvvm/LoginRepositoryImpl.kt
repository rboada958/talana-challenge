package com.app.androidev.ui.login.mvvm

import com.app.androidev.app.data.LocalDataStore
import com.app.androidev.app.model.favorite.ResponseFavorite
import com.app.androidev.app.model.login.RequestLogin
import com.app.androidev.app.model.login.ResponseLogin
import com.app.androidev.app.networks.api.LoginApi
import com.app.androidev.utils.base.DataState
import com.app.androidev.utils.runRemoteApiCall
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class LoginRepositoryImpl @Inject constructor(
    val localDataStore: LocalDataStore,
    private val api: LoginApi
) : LoginRepository {

    override suspend fun login(requestLogin: RequestLogin): Flow<DataState<ResponseLogin>> =
        flow {
            emit(DataState.Loading)
            runRemoteApiCall(
                success = {
                    emit(DataState.success(data = data))
                }
            ) {
                api.login(requestLogin)
            }
        }

    override suspend fun apiToken(token: String) =
        flow {
            localDataStore.setAccessToken(token)
            emit(true)
        }
}