package com.masscode.moviejetpack.utils

import android.widget.ImageView
import android.widget.RatingBar
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.masscode.moviejetpack.R

@BindingAdapter("showImageFromNetwork")
fun showImageFromNetwork(imgView: ImageView, url: String?) {
    Glide.with(imgView.context).load("https://image.tmdb.org/t/p/w342$url")
        .apply(RequestOptions.placeholderOf(R.color.colorAccent).error(R.color.colorAccent))
        .into(imgView)
}

@BindingAdapter("setRating")
fun setRating(ratingBar: RatingBar, float: Float) {
    ratingBar.rating = float / 2
}