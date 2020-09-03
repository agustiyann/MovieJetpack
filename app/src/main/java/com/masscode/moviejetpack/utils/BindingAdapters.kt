package com.masscode.moviejetpack.utils

import android.content.Intent
import android.net.Uri
import android.widget.Button
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.masscode.moviejetpack.R
import jp.wasabeef.glide.transformations.BlurTransformation

@BindingAdapter("showImage")
fun showImage(imgView: ImageView, url: Int) {
    Glide.with(imgView.context).load(url)
        .apply(RequestOptions.placeholderOf(R.color.colorAccent).error(R.color.colorAccent))
        .into(imgView)
}

@BindingAdapter("showBlurImage")
fun showBlurImage(imgView: ImageView, url: Int) {
    Glide.with(imgView.context).load(url)
        .apply(RequestOptions.bitmapTransform(BlurTransformation(10, 1)))
        .into(imgView)
}

@BindingAdapter("watchTrailer")
fun watchTrailer(button: Button, url: String) {
    button.setOnClickListener {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
        button.context.startActivity(intent)
    }
}