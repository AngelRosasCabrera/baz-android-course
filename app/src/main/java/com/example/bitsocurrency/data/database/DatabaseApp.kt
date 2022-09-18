package com.example.bitsocurrency.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.bitsocurrency.data.database.entities.BitsoEntity
import com.example.bitsocurrency.utils.constants.Constants.DATABASE_VERSION

@Database(entities = [BitsoEntity::class], version = DATABASE_VERSION, exportSchema = false)
abstract class DatabaseApp : RoomDatabase() {
    abstract fun dao(): DatabaseDao
}