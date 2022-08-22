package com.app.androidev.utils.adapter

import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.app.androidev.utils.base.loadRect
import com.app.androidev.utils.base.parseDate

object UtilsBindingAdapters {

    @BindingAdapter("loadUrl")
    @JvmStatic
    fun loadImage(imageView: ImageView, url: String?) {
        url?.let { imageView.loadRect(it) }
    }

    @BindingAdapter("parseDate")
    @JvmStatic
    fun date(textView: TextView, text: String) {
        textView.parseDate(text)
    }
}