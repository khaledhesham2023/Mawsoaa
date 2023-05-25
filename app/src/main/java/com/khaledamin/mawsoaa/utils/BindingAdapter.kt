package com.khaledamin.mawsoaa.utils

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.khaledamin.mawsoaa.R
import com.squareup.picasso.Picasso


@BindingAdapter("imgUrl")
fun loadImageFromUrl(imageView: ImageView, url: String?) {
//    Picasso.get().load(url).placeholder(R.drawable.ic_app_logo).into(imageView)
    Glide.with(imageView.context).load(url).placeholder(R.drawable.ic_app_logo).into(imageView)
}


