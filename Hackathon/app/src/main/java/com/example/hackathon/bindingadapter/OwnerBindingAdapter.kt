package com.example.hackathon.bindingadapter

import android.graphics.drawable.Drawable
import android.media.Rating
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions

object OwnerBindingAdapter {


    @BindingAdapter("app:imageUrl")
    @JvmStatic
    fun loadImage(imageView: ImageView, url: String) {
        Glide.with(imageView.context)
            .load(url)
            .diskCacheStrategy(DiskCacheStrategy.NONE)
            .apply(RequestOptions().fitCenter())
            .into(imageView)
    }

    @BindingAdapter("app:rating")
    @JvmStatic
    fun TextView.ratingToFloat(data: Float) {
        this.text = data.toString()
    }
}