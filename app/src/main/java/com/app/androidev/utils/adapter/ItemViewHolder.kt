package com.app.androidev.utils.adapter

import androidx.annotation.LayoutRes

interface ItemViewHolder {
    @get:LayoutRes
    val layoutId: Int
    val viewType : Int
        get() = 0
}

