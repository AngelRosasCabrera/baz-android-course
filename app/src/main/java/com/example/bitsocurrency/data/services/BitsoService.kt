package com.example.bitsocurrency.data.services

import com.example.bitsocurrency.BuildConfig
import com.example.bitsocurrency.data.services.models.BitsoResponse
import com.example.bitsocurrency.data.services.models.BookResponse
import com.example.bitsocurrency.data.services.models.TickerResponse
import kotlinx.coroutines.flow.Flow
import retrofit2.http.GET
import retrofit2.http.Query

interface BitsoService {

    @GET(BuildConfig.AVAILABLE_BOOKS)
    fun getAvailableBooks(): Flow<BitsoResponse>

    @GET(BuildConfig.TICKER)
    fun getTicker(): Flow<TickerResponse>

    @GET(BuildConfig.ORDER_BOOK)
    fun getBook(@Query("book") book: String): Flow<BookResponse>

}