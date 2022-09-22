package com.example.bitsocurrency.utils.extensions

import androidx.appcompat.widget.AppCompatImageView
import com.bumptech.glide.Glide

fun AppCompatImageView.loadUrl(url: String) = Glide.with(context).load(url).into(this)
