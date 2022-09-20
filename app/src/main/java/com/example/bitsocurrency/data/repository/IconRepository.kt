package com.example.bitsocurrency.data.repository

import com.example.bitsocurrency.data.services.models.icon.IconResponse

interface IconRepository {
    suspend fun getMapIcons(): IconResponse
}