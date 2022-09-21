package com.example.bitsocurrency.data.repository

import com.example.bitsocurrency.domain.models.Bitso

interface DatabaseRepository {
    suspend fun insertData(data: List<Bitso>)
    suspend fun getAllData(): List<Bitso>
    suspend fun deleteAllData()
}