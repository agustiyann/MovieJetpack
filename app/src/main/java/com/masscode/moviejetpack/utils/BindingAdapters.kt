package com.masscode.moviejetpack.utils

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.masscode.moviejetpack.R
import jp.wasabeef.glide.transformations.BlurTransformation

@BindingAdapter("showImageFromNetwork")
fun showImageFromNetwork(imgView: ImageView, url: String?) {
    Glide.with(imgView.context).load("https://image.tmdb.org/t/p/w342$url")
        .apply(RequestOptions.placeholderOf(R.color.colorAccent).error(R.color.colorAccent))
        .into(imgView)
}

@BindingAdapter("showBlurImage")
fun showBlurImage(imgView: ImageView, url: String?) {
    Glide.with(imgView.context).load("https://image.tmdb.org/t/p/w500$url")
        .apply(RequestOptions.bitmapTransform(BlurTransformation(10, 1)))
        .into(imgView)
}