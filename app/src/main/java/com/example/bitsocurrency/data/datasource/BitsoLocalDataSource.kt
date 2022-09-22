package com.example.bitsocurrency.data.datasource

import com.example.bitsocurrency.data.database.entities.BitsoEntity

interface BitsoLocalDataSource {
    suspend fun insertData(data: List<BitsoEntity>)
    suspend fun getAllData(): List<BitsoEntity>
    suspend fun deleteAllData()
}