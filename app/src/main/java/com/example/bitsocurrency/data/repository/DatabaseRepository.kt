package com.example.bitsocurrency.data.repository

import com.example.bitsocurrency.data.database.entities.BitsoEntity

interface DatabaseRepository {
    suspend fun insertData(data: List<BitsoEntity>)
    suspend fun getAllData(): List<BitsoEntity>
    suspend fun deleteAllData()
}