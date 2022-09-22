package com.example.bitsocurrency.domain.models

import com.example.bitsocurrency.data.services.models.bitso.BidModel

data class Bid(
    val amount: String = "",
    val book: String = "",
    val price: String = "",
)

fun BidModel.toDomain() = Bid(
    this.amount,
    this.book,
    this.price
)