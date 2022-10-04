package com.example.bitsocurrency.data.datasource

import android.util.Log
import com.example.bitsocurrency.data.database.DatabaseDao
import com.example.bitsocurrency.data.database.entities.BitsoEntity
import com.example.bitsocurrency.data.database.entities.DetailsEntity
import javax.inject.Inject

class LocalBitsoDataSource @Inject constructor(private val dao: DatabaseDao) : BitsoLocalDataSource {
    override suspend fun insertData(data: List<BitsoEntity>) = dao.insertData(data)
    override suspend fun getAllData(): List<BitsoEntity> = dao.getAllData()
    override suspend fun deleteAllData() = dao.deleteAllData()

    override fun insertDetails(data: DetailsEntity) = dao.insertDetails(data)
    override fun getAllDetails(bitsoId: Int): DetailsEntity {
        Log.d("AndroidStudio", "Bitso: $bitsoId")
        return dao.getAllDetails(bitsoId)
    }
    override fun deleteAllDetails() = dao.deleteAllDetails()

}