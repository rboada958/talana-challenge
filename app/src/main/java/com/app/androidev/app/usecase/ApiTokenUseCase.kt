package com.app.androidev.app.usecase

import com.app.androidev.ui.login.mvvm.LoginRepository
import javax.inject.Inject

class ApiTokenUseCase @Inject constructor(private val repository: LoginRepository) {

    suspend operator fun invoke(token: String) =
        repository.apiToken(token)
}