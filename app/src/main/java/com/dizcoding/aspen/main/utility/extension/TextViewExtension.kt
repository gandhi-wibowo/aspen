package com.dizcoding.aspen.main.utility.extension

import android.widget.TextView
import androidx.core.content.ContextCompat

fun TextView.setDrawableLeft(id: Int) {
    val drawable = ContextCompat.getDrawable(this.context, id)
    this.setCompoundDrawablesWithIntrinsicBounds(drawable, null, null, null)
}


fun TextView.setDrawableRight(id: Int) {
    val drawable = ContextCompat.getDrawable(this.context, id)
    this.setCompoundDrawablesWithIntrinsicBounds(null, null, drawable, null)
}