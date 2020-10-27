package com.masscode.moviejetpack.utils

import android.annotation.SuppressLint
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.masscode.moviejetpack.R

@BindingAdapter("showImageFromNetwork")
fun showImageFromNetwork(imgView: ImageView, url: String?) {
    Glide.with(imgView.context).load("https://image.tmdb.org/t/p/w780$url")
        .apply(RequestOptions.placeholderOf(R.color.colorAccent).error(R.color.colorAccent))
        .into(imgView)
}

@BindingAdapter("app:setRating")
fun setRating(ratingBar: RatingBar, rating: Float) {
    ratingBar.rating = rating / 2
}

@SuppressLint("SetTextI18n")
@BindingAdapter("app:setRatingText")
fun setRatingText(text: TextView, rating: String) {
    text.text = "Rating: $rating"
}