package com.example.bitsocurrency.domain.models

import com.example.bitsocurrency.data.services.models.bitso.BitsoModel

data class Bitso(
    val book: String = "",
    val defaultChart: String = "",
    val maximumAmount: String = "",
    val maximumPrice: String = "",
    val maximumValue: String = "",
    val minimumAmount: String = "",
    val minimumPrice: String = "",
    val minimumValue: String = "",
    val tickSize: String = ""
)

fun BitsoModel.toDomain() = Bitso(
    book = book,
    defaultChart = defaultChart,
    maximumAmount = maximumAmount,
    maximumPrice = maximumPrice,
    maximumValue = maximumValue,
    minimumAmount = minimumAmount,
    minimumPrice = minimumPrice,
    minimumValue = minimumValue,
    tickSize = tickSize
)