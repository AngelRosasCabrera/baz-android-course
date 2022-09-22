package com.example.bitsocurrency.utils.extensions

import androidx.appcompat.widget.AppCompatImageView
import com.bumptech.glide.Glide
import java.text.NumberFormat

fun AppCompatImageView.loadUrl(url: String) = Glide.with(context).load(url).into(this)

fun Double.formatAsCurrency(): String {
    return NumberFormat.getCurrencyInstance().format(this)
}

fun Double.formatAsPercent(): String {
    return NumberFormat.getPercentInstance().format(this)
}