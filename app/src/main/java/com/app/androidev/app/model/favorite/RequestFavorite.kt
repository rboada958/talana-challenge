package com.app.androidev.app.model.favorite

import com.squareup.moshi.Json

data class RequestFavorite(

    @Json(name = "postid")
    val postid: String? = null
)