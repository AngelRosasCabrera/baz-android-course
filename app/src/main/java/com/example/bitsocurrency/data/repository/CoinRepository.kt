package com.example.bitsocurrency.data.repository

import com.example.bitsocurrency.data.services.models.coin.CoinResponse

interface CoinRepository {
    suspend fun getCoinInfo(symbol: String): CoinResponse
}