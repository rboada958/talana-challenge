package com.app.androidev.ui.home.adapter

import com.app.androidev.R
import com.app.androidev.app.data.db.entities.FeedItem
import com.app.androidev.utils.adapter.ItemViewHolder

class FavoriteViewHolder(val result: FeedItem) : ItemViewHolder {
    override val layoutId: Int = R.layout.favorite_layout
    override val viewType: Int = -3
}