package com.example.bitsocurrency.data.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.bitsocurrency.domain.models.Bitso
import com.example.bitsocurrency.utils.constants.Constants.TABLE_BITSO

@Entity(tableName = TABLE_BITSO)
data class BitsoEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "bitso_id") val bitsoId: Int = 0,
    @ColumnInfo(name = "book") val book: String = "",
    @ColumnInfo(name = "default_chart") val defaultChart: String = "",
    @ColumnInfo(name = "maximum_amount") val maximumAmount: String = "",
    @ColumnInfo(name = "maximum_price") val maximumPrice: String = "",
    @ColumnInfo(name = "maximum_value") val maximumValue: String = "",
    @ColumnInfo(name = "minimum_amount") val minimumAmount: String = "",
    @ColumnInfo(name = "minimum_price") val minimumPrice: String = "",
    @ColumnInfo(name = "minimum_value") val minimumValue: String = "",
    @ColumnInfo(name = "tick_size") val tickSize: String = ""
)

fun Bitso.toDatabase() = BitsoEntity(
    book = book,
    defaultChart = defaultChart,
    maximumAmount = maximumAmount,
    maximumPrice = maximumPrice,
    maximumValue = maximumValue,
    minimumAmount = minimumAmount,
    minimumPrice = minimumPrice,
    minimumValue = minimumValue,
    tickSize = tickSize
)