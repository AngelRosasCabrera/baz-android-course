package com.example.bitsocurrency.data.services.models.coin

import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName

data class CoinResponse(
    @SerializedName("data") @Expose val `data`: HashMap<String, Array<DetailsResponse>> = hashMapOf(),
    @SerializedName("status") @Expose val status: StatusResponse
)

data class StatusResponse(
    @SerializedName("credit_count") @Expose val creditCount: Int = 0,
    @SerializedName("elapsed") @Expose val elapsed: Int = 0,
    @SerializedName("error_code") @Expose val errorCode: Int = 0,
    @SerializedName("error_message") @Expose val errorMessage: String = "",
    @SerializedName("notice") @Expose val notice: String = "",
    @SerializedName("timestamp") @Expose val timestamp: String = ""
)

data class DetailsResponse(
    @SerializedName("id") @Expose val id: Int = 0,
    @SerializedName("name") @Expose val name: String = "",
    @SerializedName("logo") @Expose val logo: String = "",
    @SerializedName("category") @Expose val category: String = "",
    @SerializedName("description") @Expose val description: String = "",
    @SerializedName("date_added") @Expose val dateAdded: String = "",
)