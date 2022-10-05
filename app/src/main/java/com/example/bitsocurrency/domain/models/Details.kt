package com.example.bitsocurrency.domain.models

data class Details(
    val bitsoId: Int,
    val tickers: Ticker,
    val book: Book,
    val fromDatabase: Boolean = false
)