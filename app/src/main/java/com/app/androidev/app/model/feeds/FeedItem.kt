package com.app.androidev.app.model.feeds

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.parcelize.Parcelize

@Parcelize
data class FeedItem(

    @Json(name = "date")
    val date: String? = null,

    @Json(name = "image")
    val image: String? = null,

    @Json(name = "description")
    val description: String? = null,

    @Json(name = "id")
    val id: Int? = null,

    @Json(name = "published")
    val published: String? = null,

    @Json(name = "title")
    val title: String? = null,

    @Json(name = "author_id")
    val authorId: String? = null,

    @Json(name = "link")
    val link: String? = null
) : Parcelable