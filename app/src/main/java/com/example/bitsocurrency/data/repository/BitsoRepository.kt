package com.example.bitsocurrency.data.repository

import com.example.bitsocurrency.data.services.models.BookModel
import com.example.bitsocurrency.data.services.models.TickerModel
import com.example.bitsocurrency.domain.models.Bitso
import retrofit2.http.Query

interface BitsoRepository {
    suspend fun getAvailableBooks(): List<Bitso>
    suspend fun getTicker(): TickerModel
    suspend fun getBook(@Query("book") book: String): BookModel
}