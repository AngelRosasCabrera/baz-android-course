package com.example.bitsocurrency.data.repository

import com.example.bitsocurrency.data.services.BitsoService
import com.example.bitsocurrency.data.services.models.BitsoModel
import com.example.bitsocurrency.data.services.models.BookModel
import com.example.bitsocurrency.data.services.models.TickerModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow
import javax.inject.Inject

class BitsoRepositoryImpl @Inject constructor(private val service: BitsoService) : BitsoRepository {
    override fun getAvailableBooks(): Flow<List<BitsoModel>> {
        return emptyFlow()
    }

    override fun getTicker(): Flow<TickerModel> {
        TODO("Not yet implemented")
    }

    override fun getBook(book: String): Flow<BookModel> {
        TODO("Not yet implemented")
    }
}