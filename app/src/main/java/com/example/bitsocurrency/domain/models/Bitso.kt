package com.example.bitsocurrency.domain.models

data class Bitso(
    val book: String,
    val defaultChart: String,
    val fees: Fees,
    val maximumAmount: String,
    val maximumPrice: String,
    val maximumValue: String,
    val minimumAmount: String,
    val minimumPrice: String,
    val minimumValue: String,
    val tickSize: String
)

data class Fees(val flatRate: FlatRate, val structure: List<Structure>)

data class FlatRate(val maker: String, val taker: String)

data class Structure(val maker: String, val taker: String, val volume: String)