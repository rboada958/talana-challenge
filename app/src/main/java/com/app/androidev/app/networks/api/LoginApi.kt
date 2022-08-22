package com.app.androidev.app.networks.api


import com.app.androidev.app.model.login.RequestLogin
import com.app.androidev.app.model.login.ResponseLogin
import com.skydoves.sandwich.ApiResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface LoginApi {

    @POST("/api/login")
    suspend fun login(@Body body: RequestLogin) : ApiResponse<ResponseLogin>
}