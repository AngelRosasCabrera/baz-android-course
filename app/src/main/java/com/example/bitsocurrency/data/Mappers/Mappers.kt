package com.example.bitsocurrency.data.mappers

import com.example.bitsocurrency.data.database.entities.BitsoEntity
import com.example.bitsocurrency.data.database.entities.DetailsEntity
import com.example.bitsocurrency.data.services.models.bitso.BitsoModel
import com.example.bitsocurrency.data.services.models.bitso.RollingAverageChangeModel
import com.example.bitsocurrency.data.services.models.bitso.TickerModel
import com.example.bitsocurrency.data.services.models.icon.IconResponseItem
import com.example.bitsocurrency.domain.models.Details
import com.example.bitsocurrency.domain.models.RollingAverageChange
import com.example.bitsocurrency.domain.models.Ticker

fun BitsoModel.toDatabase(icon: IconResponseItem) = BitsoEntity(
    book = book,
    defaultChart = defaultChart,
    maximumAmount = maximumAmount,
    maximumPrice = maximumPrice,
    maximumValue = maximumValue,
    minimumAmount = minimumAmount,
    minimumPrice = minimumPrice,
    minimumValue = minimumValue,
    tickSize = tickSize,
    imgUrl = icon.imgUrl,
    name = icon.name,
    symbol = icon.symbol,
)

fun TickerModel.toDomain() = Ticker(
    ask = ask,
    bid = bid,
    book = book,
    change24 = change24,
    createdAt = createdAt,
    high = high,
    last = last,
    low = low,
    rollingAverageChange = rollingAverageChange.toDomain(),
    volume = volume,
    vwap = vwap,
)

fun RollingAverageChangeModel.toDomain() = RollingAverageChange(x6 = x6)

fun Details.toDatabase() = DetailsEntity(
    bitsoId = bitsoId,
    tickers = tickers,
    book = book,
    fromDatabase = true
)

fun DetailsEntity.toDomain() = Details(
    bitsoId = bitsoId,
    tickers = tickers,
    book = book,
    fromDatabase = fromDatabase
)
