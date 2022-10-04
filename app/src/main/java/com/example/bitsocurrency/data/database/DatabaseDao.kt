package com.example.bitsocurrency.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.bitsocurrency.data.database.entities.BitsoEntity
import com.example.bitsocurrency.data.database.entities.DetailsEntity
import com.example.bitsocurrency.utils.constants.Constants.TABLE_BITSO
import com.example.bitsocurrency.utils.constants.Constants.TABLE_DETAILS

@Dao
interface DatabaseDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertData(data: List<BitsoEntity>)

    @Query("SELECT * FROM $TABLE_BITSO")
    suspend fun getAllData(): List<BitsoEntity>

    @Query("DELETE FROM $TABLE_BITSO")
    suspend fun deleteAllData()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertDetails(data: DetailsEntity)

    @Query("SELECT * FROM $TABLE_DETAILS WHERE bitso_id = :detailId")
    fun getAllDetails(detailId: Int): DetailsEntity

    @Query("DELETE FROM $TABLE_DETAILS")
    fun deleteAllDetails()
}