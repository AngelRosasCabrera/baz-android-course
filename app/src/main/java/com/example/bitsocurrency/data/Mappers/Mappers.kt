package com.example.bitsocurrency.data.mappers

import com.example.bitsocurrency.data.database.entities.BitsoEntity
import com.example.bitsocurrency.data.services.models.bitso.BitsoModel
import com.example.bitsocurrency.data.services.models.icon.IconResponseItem

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