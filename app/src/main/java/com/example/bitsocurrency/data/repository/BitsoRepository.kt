package com.example.bitsocurrency.data.repository

import com.example.bitsocurrency.data.services.models.bitso.BookModel
import com.example.bitsocurrency.data.services.models.bitso.TickerModel
import com.example.bitsocurrency.domain.models.Bitso
import retrofit2.http.Query

interface BitsoRepository {
    suspend fun getAvailableBooks(): List<Bitso>
    suspend fun getTicker(): TickerModel
    suspend fun getBook(@Query("book") book: String): BookModel
}