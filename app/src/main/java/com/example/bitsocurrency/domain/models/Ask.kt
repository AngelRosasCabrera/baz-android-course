package com.example.bitsocurrency.domain.models

import com.example.bitsocurrency.data.services.models.bitso.AskModel

data class Ask(
    val amount: String = "",
    val book: String = "",
    val price: String = "",
)

fun AskModel.toDomain() = Ask(
    this.amount,
    this.book,
    this.price
)