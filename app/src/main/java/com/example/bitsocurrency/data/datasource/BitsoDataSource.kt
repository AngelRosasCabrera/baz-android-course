package com.example.bitsocurrency.data.datasource

import com.example.bitsocurrency.data.services.models.bitso.BitsoResponse
import com.example.bitsocurrency.data.services.models.bitso.BookResponse
import com.example.bitsocurrency.data.services.models.bitso.TickerResponse
import com.example.bitsocurrency.data.services.models.icon.IconResponse
import retrofit2.http.Query

interface BitsoDataSource {
    suspend fun getAvailableBooks(): BitsoResponse
    suspend fun getTicker(): TickerResponse
    suspend fun getBook(@Query("book") book: String): BookResponse

    suspend fun getMapIcons(): IconResponse
}