package com.app.androidev.app.model.favorite

import com.squareup.moshi.Json

data class ResponseFavorite(

    @Json(name = "STATUS")
    val status: String? = null
)