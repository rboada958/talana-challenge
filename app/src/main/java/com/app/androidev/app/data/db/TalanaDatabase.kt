package com.app.androidev.app.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.app.androidev.app.data.db.dao.FeedFavoriteDao
import com.app.androidev.app.data.db.entities.FeedItem

@Database(entities = [FeedItem::class], version = 1)
abstract class TalanaDatabase : RoomDatabase() {
    abstract fun feedFavoriteDao(): FeedFavoriteDao
}