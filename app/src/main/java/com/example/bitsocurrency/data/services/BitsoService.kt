package com.example.bitsocurrency.data.services

import com.example.bitsocurrency.BuildConfig.*
import com.example.bitsocurrency.data.services.models.bitso.BitsoResponse
import com.example.bitsocurrency.data.services.models.bitso.BookResponse
import com.example.bitsocurrency.data.services.models.bitso.TickerResponse
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface BitsoService {

    @GET(AVAILABLE_BOOKS)
    suspend fun getAvailableBooks(): BitsoResponse

    @GET(TICKER)
    fun getTicker(@Query("book") book: String): Single<TickerResponse>

    @GET(ORDER_BOOK)
    fun getBook(@Query("book") book: String): Single<BookResponse>

}