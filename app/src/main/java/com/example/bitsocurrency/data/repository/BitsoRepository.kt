package com.example.bitsocurrency.data.repository

import com.example.bitsocurrency.data.services.models.bitso.TickerModel
import com.example.bitsocurrency.domain.models.Bitso
import com.example.bitsocurrency.domain.models.Book
import io.reactivex.rxjava3.core.Observable
import retrofit2.http.Query

interface BitsoRepository {
    suspend fun getAvailableBooks(): List<Bitso>
    suspend fun getTicker(): TickerModel
    fun getBook(@Query("book") book: String): Observable<Book>
}