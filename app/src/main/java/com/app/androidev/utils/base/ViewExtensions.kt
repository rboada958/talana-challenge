package com.app.androidev.utils.base

import android.annotation.SuppressLint
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.app.androidev.R
import com.bumptech.glide.Glide
import java.text.SimpleDateFormat

fun ImageView.loadRect(urlImage: String?) {
    Glide.with(context)
        .load(urlImage)
        .placeholder(R.drawable.ic_launcher_background)
        .centerCrop()
        .into(this)
}

@SuppressLint("NewApi")
fun TextView.parseDate(value: String) {
    this.text = SimpleDateFormat("yyyy-MM-dd").parse(
        value
    )?.let { date ->
        SimpleDateFormat("YYYY-MM-dd").format(
            date
        )
    }
}

fun View.visible() {
    this.visibility = View.VISIBLE
}

fun View.gone() {
    this.visibility = View.GONE
}

fun View.invisible() {
    this.visibility = View.INVISIBLE
}
