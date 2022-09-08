package com.example.bitsocurrency.data.services.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


data class BookResponse(
    @SerializedName("payload") @Expose val payload: BookModel,
    @SerializedName("success") @Expose val success: Boolean
)

data class BookModel(
    @SerializedName("asks") @Expose val asks: List<AskModel>,
    @SerializedName("bids") @Expose val bids: List<BidModel>,
    @SerializedName("sequence") @Expose val sequence: String,
    @SerializedName("updated_at") @Expose val updatedAt: String
)

data class AskModel(
    @SerializedName("amount") @Expose val amount: String,
    @SerializedName("book") @Expose val book: String,
    @SerializedName("price") @Expose val price: String
)

data class BidModel(
    @SerializedName("amount") @Expose val amount: String,
    @SerializedName("book") @Expose val book: String,
    @SerializedName("price") @Expose val price: String
)