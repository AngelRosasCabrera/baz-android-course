package com.example.bitsocurrency.data.repository

import com.example.bitsocurrency.data.services.BitsoService
import com.example.bitsocurrency.data.services.models.BookModel
import com.example.bitsocurrency.data.services.models.TickerModel
import com.example.bitsocurrency.domain.models.Bitso
import com.example.bitsocurrency.domain.models.toDomain
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class BitsoRepositoryImpl @Inject constructor(private val service: BitsoService) : BitsoRepository {
    override suspend fun getAvailableBooks(): List<Bitso> = withContext(Dispatchers.IO) {
        service.getAvailableBooks().payload.map { it.toDomain() }
    }

    override suspend fun getTicker(): TickerModel {
        TODO("Not yet implemented")
    }

    override suspend fun getBook(book: String): BookModel {
        TODO("Not yet implemented")
    }

}