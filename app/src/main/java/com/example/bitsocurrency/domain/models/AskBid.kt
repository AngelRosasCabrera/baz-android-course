package com.example.bitsocurrency.domain.models

import com.example.bitsocurrency.data.services.models.bitso.AskModel
import com.example.bitsocurrency.data.services.models.bitso.BidModel

data class AskBid(
    val amount: String = "",
    val book: String = "",
    val price: String = "",
)

fun BidModel.toDomain() = AskBid(
    this.amount,
    this.book,
    this.price
)

fun AskModel.toDomain() = AskBid(
    this.amount,
    this.book,
    this.price
)