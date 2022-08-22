package com.app.androidev.app.model.login

import com.squareup.moshi.Json

data class RequestLogin(

    @Json(name = "username")
    val username: String? = null,

    @Json(name = "password")
    val password: String? = null
)