package com.masscode.moviejetpack.utils

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.masscode.moviejetpack.R

@BindingAdapter("showImage")
fun showImage(imgView: ImageView, url: Int) {
    Glide.with(imgView.context).load(url)
        .apply(RequestOptions.placeholderOf(R.color.colorAccent).error(R.color.colorAccent))
        .into(imgView)
}