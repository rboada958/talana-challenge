package com.app.androidev.app.data.db.dao

import androidx.room.*
import com.app.androidev.app.data.db.entities.FeedItem

@Dao
interface FeedFavoriteDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(feedItem: FeedItem)

    @Query("SELECT * FROM feed_favorite")
    suspend fun getFavoriteList(): MutableList<FeedItem>

    @Query("SELECT * FROM feed_favorite WHERE feed_favorite._id = :favoriteId")
    suspend fun getFavorite(favoriteId: Int): FeedItem

    @Query("DELETE FROM feed_favorite WHERE feed_favorite._id = :favoriteId")
    suspend fun deleteFavorite(favoriteId: Int)

}