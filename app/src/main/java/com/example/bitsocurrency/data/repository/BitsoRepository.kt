package com.example.bitsocurrency.data.repository

import com.example.bitsocurrency.data.services.models.*
import kotlinx.coroutines.flow.Flow
import retrofit2.http.Query

interface BitsoRepository {
    fun getAvailableBooks(): Flow<List<BitsoModel>>
    fun getTicker(): Flow<TickerModel>
    fun getBook(@Query("book") book: String): Flow<BookModel>
}