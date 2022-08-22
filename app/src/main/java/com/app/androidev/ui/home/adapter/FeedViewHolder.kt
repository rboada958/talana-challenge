package com.app.androidev.ui.home.adapter

import com.app.androidev.R
import com.app.androidev.app.model.feeds.FeedItem
import com.app.androidev.utils.adapter.ItemViewHolder

class FeedViewHolder(val result: FeedItem, val onClick : ((FeedItem) -> Unit)? = null) : ItemViewHolder {
    override val layoutId: Int = R.layout.feed_layout
    override val viewType: Int = -3
}