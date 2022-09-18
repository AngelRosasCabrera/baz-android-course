package com.example.bitsocurrency.data.repository

import com.example.bitsocurrency.data.services.BitsoService
import com.example.bitsocurrency.data.services.models.bitso.BookModel
import com.example.bitsocurrency.data.services.models.bitso.TickerModel
import com.example.bitsocurrency.domain.models.Bitso
import com.example.bitsocurrency.domain.models.toDomain
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class BitsoRepositoryImpl @Inject constructor(
    private val service: BitsoService,
    private val dispatcher: CoroutineDispatcher
) : BitsoRepository {
    override suspend fun getAvailableBooks(): List<Bitso> = withContext(dispatcher) {
        service.getAvailableBooks().payload.map { it.toDomain() }
    }

    override suspend fun getTicker(): TickerModel {
        TODO("Not yet implemented")
    }

    override suspend fun getBook(book: String): BookModel {
        TODO("Not yet implemented")
    }

}