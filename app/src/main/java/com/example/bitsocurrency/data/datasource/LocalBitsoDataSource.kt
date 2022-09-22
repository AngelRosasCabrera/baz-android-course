package com.example.bitsocurrency.data.datasource

import com.example.bitsocurrency.data.database.DatabaseDao
import com.example.bitsocurrency.data.database.entities.BitsoEntity
import javax.inject.Inject

class LocalBitsoDataSource @Inject constructor(private val dao: DatabaseDao) : BitsoLocalDataSource {
    override suspend fun insertData(data: List<BitsoEntity>) = dao.insertData(data)
    override suspend fun getAllData(): List<BitsoEntity> = dao.getAllData()
    override suspend fun deleteAllData() = dao.deleteAllData()
}