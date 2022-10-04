package com.example.bitsocurrency.data.repository

import com.example.bitsocurrency.domain.models.Bitso
import com.example.bitsocurrency.domain.models.Book
import com.example.bitsocurrency.domain.models.Details
import com.example.bitsocurrency.domain.models.Ticker
import io.reactivex.rxjava3.core.Single
import retrofit2.http.Query

interface BitsoRepository {
    suspend fun getAvailableBooks(): List<Bitso>
    fun getTicker(book: String): Single<Ticker>
    fun getBook(@Query("book") book: String): Single<Book>

    fun insertDetails(data: Details)
    fun getDetails(bitsoId: Int): Details
    fun deleteAllDetails()
}