package com.example.bitsocurrency.data.services.models.bitso

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class BitsoResponse(
    @SerializedName("payload") @Expose val payload: List<BitsoModel> = listOf(),
    @SerializedName("success") @Expose val success: Boolean = false,
)

data class BitsoModel(
    @SerializedName("book") @Expose val book: String = "",
    @SerializedName("default_chart") @Expose val defaultChart: String = "",
    @SerializedName("fees") @Expose val fees: FeesModel = FeesModel(),
    @SerializedName("maximum_amount") @Expose val maximumAmount: String = "",
    @SerializedName("maximum_price") @Expose val maximumPrice: String = "",
    @SerializedName("maximum_value") @Expose val maximumValue: String = "",
    @SerializedName("minimum_amount") @Expose val minimumAmount: String = "",
    @SerializedName("minimum_price") @Expose val minimumPrice: String = "",
    @SerializedName("minimum_value") @Expose val minimumValue: String = "",
    @SerializedName("tick_size") @Expose val tickSize: String = "",
)

data class FeesModel(
    @SerializedName("flat_rate") @Expose val flatRate: FlatRateModel = FlatRateModel(),
    @SerializedName("structure") @Expose val structure: List<StructureModel> = listOf(),
)

data class FlatRateModel(
    @SerializedName("maker") @Expose val maker: String = "",
    @SerializedName("taker") @Expose val taker: String = "",
)

data class StructureModel(
    @SerializedName("maker") @Expose val maker: String = "",
    @SerializedName("taker") @Expose val taker: String = "",
    @SerializedName("volume") @Expose val volume: String = "",
)
