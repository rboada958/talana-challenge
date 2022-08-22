package com.app.androidev.app.usecase

import com.app.androidev.app.model.login.RequestLogin
import com.app.androidev.ui.login.mvvm.LoginRepository
import javax.inject.Inject

class LoginUseCase @Inject constructor(private val repository: LoginRepository) {

    suspend operator fun invoke(requestLogin: RequestLogin) =
        repository.login(requestLogin)
}