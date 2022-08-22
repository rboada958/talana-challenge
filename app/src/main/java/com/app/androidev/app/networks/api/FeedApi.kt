package com.app.androidev.app.networks.api

import com.app.androidev.app.model.favorite.RequestFavorite
import com.app.androidev.app.model.favorite.ResponseFavorite
import com.app.androidev.app.model.feeds.FeedItem
import com.skydoves.sandwich.ApiResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface FeedApi {

    @GET("/api/feed")
    suspend fun feed() : ApiResponse<List<FeedItem>>

    @POST("/api/favorite")
    suspend fun favorite(@Body body: RequestFavorite): ApiResponse<ResponseFavorite>
}