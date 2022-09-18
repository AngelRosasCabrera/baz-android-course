package com.example.bitsocurrency.data.repository

import com.example.bitsocurrency.data.services.CoinService
import com.example.bitsocurrency.data.services.models.coin.CoinResponse
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class CoinRepositoryImpl @Inject constructor(
    private val service: CoinService,
    private val dispatcher: CoroutineDispatcher
) : CoinRepository {
    override suspend fun getCoinInfo(symbol: String): CoinResponse = withContext(dispatcher) {
        service.getCoinInfo(symbol)
    }
}