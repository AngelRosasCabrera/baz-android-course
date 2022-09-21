package com.example.bitsocurrency.data.repository

import com.example.bitsocurrency.data.database.DatabaseDao
import com.example.bitsocurrency.data.database.entities.toDatabase
import com.example.bitsocurrency.domain.models.Bitso
import com.example.bitsocurrency.domain.models.toDomain
import javax.inject.Inject

class DatabaseRepositoryImpl @Inject constructor(private val dao: DatabaseDao) : DatabaseRepository {
    override suspend fun insertData(data: List<Bitso>) {
        dao.insertData(data.map { it.toDatabase() })
    }

    override suspend fun getAllData(): List<Bitso> {
        return dao.getAllData().map { it.toDomain() }
    }

    override suspend fun deleteAllData() {
        dao.deleteAllData()
    }
}