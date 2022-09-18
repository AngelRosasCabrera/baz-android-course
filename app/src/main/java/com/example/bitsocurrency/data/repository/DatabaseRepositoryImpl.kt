package com.example.bitsocurrency.data.repository

import com.example.bitsocurrency.data.database.DatabaseDao
import com.example.bitsocurrency.data.database.entities.BitsoEntity
import javax.inject.Inject

class DatabaseRepositoryImpl @Inject constructor(private val dao: DatabaseDao) : DatabaseRepository {
    override suspend fun insertData(data: List<BitsoEntity>) {
        dao.insertData(data)
    }

    override suspend fun getAllData(): List<BitsoEntity> {
        return dao.getAllData()
    }

    override suspend fun deleteAllData() {
        dao.deleteAllData()
    }
}