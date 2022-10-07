package com.example.bitsocurrency.data.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.bitsocurrency.domain.models.Book
import com.example.bitsocurrency.domain.models.Ticker
import com.example.bitsocurrency.utils.constants.Constants.TABLE_DETAILS

@Entity(tableName = TABLE_DETAILS)
class DetailsEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "detail_id") val detailsId: Int = 0,
    @ColumnInfo(name = "bitso_id") val bitsoId: Int = 0,
    @ColumnInfo(name = "tickers") val tickers: Ticker,
    @ColumnInfo(name = "book") val book: Book,
    @ColumnInfo(name = "from_database") val fromDatabase: Boolean = false
)