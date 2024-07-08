package com.dizcoding.aspen.main.utility.extension

import android.graphics.drawable.Drawable
import android.widget.ImageView
import androidx.annotation.DimenRes
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target

fun ImageView.loadImage(source: Any) {
    loadImage(source) {}
}

fun ImageView.loadImage(source: Any, onLoadError: (Boolean) -> Unit) {
    Glide
        .with(this)
        .load(source)
        .listener(object : RequestListener<Drawable> {
            override fun onLoadFailed(
                e: GlideException?,
                model: Any?,
                target: Target<Drawable>?,
                isFirstResource: Boolean
            ): Boolean {
                onLoadError.invoke(true)
                return false
            }

            override fun onResourceReady(
                resource: Drawable?,
                model: Any?,
                target: Target<Drawable>?,
                dataSource: DataSource?,
                isFirstResource: Boolean
            ): Boolean {
                onLoadError.invoke(false)
                return false
            }

        })
        .into(this)
}

fun ImageView.loadImage(
    source: Any,
    @DimenRes rad: Int
) {
    loadImage(source, rad) {}
}

fun ImageView.loadImage(
    source: Any,
    @DimenRes rad: Int,
    onLoadError: (Boolean) -> Unit
) {
    val radius = context.resources.getDimensionPixelSize(rad)
    Glide
        .with(this)
        .load(source)
        .transform(RoundedCorners(radius))
        .listener(object : RequestListener<Drawable> {
            override fun onLoadFailed(
                e: GlideException?,
                model: Any?,
                target: Target<Drawable>?,
                isFirstResource: Boolean
            ): Boolean {
                onLoadError.invoke(true)
                return false
            }

            override fun onResourceReady(
                resource: Drawable?,
                model: Any?,
                target: Target<Drawable>?,
                dataSource: DataSource?,
                isFirstResource: Boolean
            ): Boolean {
                onLoadError.invoke(false)
                return false
            }

        })
        .into(this)
}