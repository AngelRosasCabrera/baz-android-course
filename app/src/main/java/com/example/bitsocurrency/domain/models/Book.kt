package com.example.bitsocurrency.domain.models

import com.example.bitsocurrency.data.services.models.bitso.BookModel

data class Book(
    val asks: List<AskBid> = listOf(),
    val bids: List<AskBid> = listOf(),
    val sequence: String = "",
    val updatedAt: String = "",
)

fun BookModel.toDomain() = Book(
    asks = asks.map { it.toDomain() },
    bids = bids.map { it.toDomain() },
    this.sequence,
    this.updatedAt,
)