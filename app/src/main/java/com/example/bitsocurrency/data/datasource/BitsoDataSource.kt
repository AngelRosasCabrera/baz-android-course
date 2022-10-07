package com.example.bitsocurrency.data.datasource

import com.example.bitsocurrency.data.services.models.bitso.BitsoResponse
import com.example.bitsocurrency.data.services.models.bitso.BookResponse
import com.example.bitsocurrency.data.services.models.bitso.TickerResponse
import com.example.bitsocurrency.data.services.models.icon.IconResponse
import io.reactivex.rxjava3.core.Single

interface BitsoDataSource {
    suspend fun getAvailableBooks(): BitsoResponse
    fun getTicker(book: String): Single<TickerResponse>
    fun getBook(book: String): Single<BookResponse>

    suspend fun getMapIcons(): IconResponse
}