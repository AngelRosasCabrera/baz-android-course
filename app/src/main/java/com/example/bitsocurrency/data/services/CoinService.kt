package com.example.bitsocurrency.data.services

import com.example.bitsocurrency.BuildConfig.API_COIN_INFO
import com.example.bitsocurrency.BuildConfig.API_COIN_HEADER
import com.example.bitsocurrency.BuildConfig.KEY_COIN_MARKET
import com.example.bitsocurrency.data.services.models.coin.CoinResponse
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface CoinService {

    @Headers("$API_COIN_HEADER: $KEY_COIN_MARKET")
    @GET(API_COIN_INFO)
    suspend fun getCoinInfo(@Query("symbol") symbol: String): CoinResponse

}