package com.example.bitsocurrency.data.services.models.icon

import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName

class IconResponse : ArrayList<IconResponseItem>()

data class IconResponseItem(
    @SerializedName("img_url") @Expose val imgUrl: String = "",
    @SerializedName("name") @Expose val name: String = "",
    @SerializedName("slug") @Expose val slug: String = "",
    @SerializedName("symbol") @Expose val symbol: String = ""
)