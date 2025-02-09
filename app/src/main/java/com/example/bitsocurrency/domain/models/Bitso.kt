package com.example.bitsocurrency.domain.models

import android.os.Parcelable
import com.example.bitsocurrency.data.database.entities.BitsoEntity
import kotlinx.parcelize.Parcelize

@Parcelize
data class Bitso(
    val book: String = "",
    val defaultChart: String = "",
    val maximumAmount: String = "",
    val maximumPrice: String = "",
    val maximumValue: String = "",
    val minimumAmount: String = "",
    val minimumPrice: String = "",
    val minimumValue: String = "",
    val tickSize: String = "",
    val imgUrl: String = "",
    val name: String = "",
    val symbol: String = ""
) : Parcelable

fun BitsoEntity.toDomain() = Bitso(
    book = book,
    defaultChart = defaultChart,
    maximumAmount = maximumAmount,
    maximumPrice = maximumPrice,
    maximumValue = maximumValue,
    minimumAmount = minimumAmount,
    minimumPrice = minimumPrice,
    minimumValue = minimumValue,
    tickSize = tickSize,
    imgUrl = imgUrl,
    name = name,
    symbol = symbol,
)