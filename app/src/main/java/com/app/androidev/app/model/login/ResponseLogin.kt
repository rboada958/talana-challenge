package com.app.androidev.app.model.login

import com.google.gson.annotations.SerializedName
import com.squareup.moshi.Json

data class ResponseLogin(

	@Json(name="STATUS")
	val status: String? = null,

	@SerializedName("api-token") @Json(name="api-token") val apiToken: String? = null
)