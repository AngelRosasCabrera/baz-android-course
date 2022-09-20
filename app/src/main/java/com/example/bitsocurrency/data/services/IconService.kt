package com.example.bitsocurrency.data.services

import com.example.bitsocurrency.BuildConfig.GITHUB_DETAILS
import com.example.bitsocurrency.data.services.models.icon.IconResponse
import retrofit2.http.GET

interface IconService {
    @GET(GITHUB_DETAILS)
    suspend fun getMapIcons(): IconResponse
}