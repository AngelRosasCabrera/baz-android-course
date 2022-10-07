package com.example.bitsocurrency.domain.models


data class Ticker(
    val ask: String = "",
    val bid: String = "",
    val book: String = "",
    val change24: String = "",
    val createdAt: String = "",
    val high: String = "",
    val last: String = "",
    val low: String = "",
    val rollingAverageChange: RollingAverageChange = RollingAverageChange(),
    val volume: String = "",
    val vwap: String = "",
)

data class RollingAverageChange(
    val x6: String = "",
)