package com.example.bitsocurrency.domain.models

import com.example.bitsocurrency.data.services.models.BitsoModel
import com.example.bitsocurrency.data.services.models.FeesModel
import com.example.bitsocurrency.data.services.models.FlatRateModel
import com.example.bitsocurrency.data.services.models.StructureModel

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
    val tickSize: String,
)

data class Fees(val flatRate: FlatRate, val structure: List<Structure>)

data class FlatRate(val maker: String, val taker: String)

data class Structure(val maker: String, val taker: String, val volume: String)


fun BitsoModel.toDomain() = Bitso(
    book = book,
    defaultChart = defaultChart,
    fees = fees.toDomain(),
    maximumAmount = maximumAmount,
    maximumPrice = maximumPrice,
    maximumValue = maximumValue,
    minimumAmount = minimumAmount,
    minimumPrice = minimumPrice,
    minimumValue = minimumValue,
    tickSize = tickSize
)

fun FeesModel.toDomain() = Fees(
    flatRate = flatRate.toDomain(),
    structure = structure.map { it.toDomain() }
)

fun FlatRateModel.toDomain() = FlatRate(
    maker = maker,
    taker = taker
)

fun StructureModel.toDomain() = Structure(
    maker = maker,
    taker = taker,
    volume = volume
)