package com.example.bitsocurrency.data.datasource

import com.example.bitsocurrency.data.database.entities.BitsoEntity
import com.example.bitsocurrency.data.database.entities.DetailsEntity

interface BitsoLocalDataSource {
    suspend fun insertData(data: List<BitsoEntity>)
    suspend fun getAllData(): List<BitsoEntity>
    suspend fun deleteAllData()

    fun insertDetails(data: DetailsEntity)
    fun getAllDetails(bitsoId: Int): DetailsEntity
    fun deleteAllDetails()
}