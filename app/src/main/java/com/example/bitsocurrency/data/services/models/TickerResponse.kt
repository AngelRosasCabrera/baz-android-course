package com.example.bitsocurrency.data.services.models
import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName


data class TickerResponse(
    @SerializedName("payload") @Expose val payload: List<TickerModel>,
    @SerializedName("success") @Expose val success: Boolean
)

data class TickerModel(
    @SerializedName("ask") @Expose val ask: String,
    @SerializedName("bid") @Expose val bid: String,
    @SerializedName("book") @Expose val book: String,
    @SerializedName("change_24") @Expose val change24: String,
    @SerializedName("created_at") @Expose val createdAt: String,
    @SerializedName("high") @Expose val high: String,
    @SerializedName("last") @Expose val last: String,
    @SerializedName("low") @Expose val low: String,
    @SerializedName("rolling_average_change") @Expose val rollingAverageChange: RollingAverageChangeModel,
    @SerializedName("volume") @Expose val volume: String,
    @SerializedName("vwap") @Expose val vwap: String
)

data class RollingAverageChangeModel(
    @SerializedName("6") @Expose val x6: String
)