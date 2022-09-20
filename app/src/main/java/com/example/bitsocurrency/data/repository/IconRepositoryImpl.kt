package com.example.bitsocurrency.data.repository

import com.example.bitsocurrency.data.services.IconService
import com.example.bitsocurrency.data.services.models.icon.IconResponse
import javax.inject.Inject

class IconRepositoryImpl @Inject constructor(private val service: IconService) : IconRepository {
    override suspend fun getMapIcons(): IconResponse {
        return service.getMapIcons()
    }
}