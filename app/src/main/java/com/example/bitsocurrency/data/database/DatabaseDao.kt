package com.example.bitsocurrency.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.bitsocurrency.data.database.entities.BitsoEntity
import com.example.bitsocurrency.utils.constants.Constants.TABLE_BITSO

@Dao
interface DatabaseDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertData(data: List<BitsoEntity>)

    @Query("SELECT * FROM $TABLE_BITSO")
    suspend fun getAllData(): List<BitsoEntity>

    @Query("DELETE FROM $TABLE_BITSO")
    suspend fun deleteAllData()
}