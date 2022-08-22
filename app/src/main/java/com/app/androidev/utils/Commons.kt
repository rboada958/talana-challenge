package com.app.androidev.utils

import android.os.Build
import com.app.androidev.app.AndroidevApp

object Commons {

    fun getString(string : Int) : String {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
            AndroidevApp.appContext.getString(string)
        else
            AndroidevApp.appContext.resources.getString(string)
    }
}