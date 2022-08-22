package com.app.androidev.app.data.db.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "feed_favorite")
data class FeedItem(
    val date: String = "",
    val image: String = "",
    val description: String = "",
    val published: String = "",
    val title: String = "",
    val authorId: String = "",
    val link: String = ""
) {
    @PrimaryKey(autoGenerate = true)
    var _id : Int = 0
}