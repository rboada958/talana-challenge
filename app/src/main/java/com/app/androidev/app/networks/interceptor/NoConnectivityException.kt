package com.app.androidev.app.networks.interceptor

import com.app.androidev.R
import com.app.androidev.utils.Commons
import okio.IOException

class NoConnectivityException : IOException(){
    override val message: String
        get() = Commons.getString(R.string.connectivity_exception)
}