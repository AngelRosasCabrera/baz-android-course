package com.example.bitsocurrency.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.bitsocurrency.data.database.converters.BookConverter
import com.example.bitsocurrency.data.database.converters.TickerConverter
import com.example.bitsocurrency.data.database.entities.BitsoEntity
import com.example.bitsocurrency.data.database.entities.DetailsEntity
import com.example.bitsocurrency.utils.constants.Constants.DATABASE_VERSION

@Database(
    entities = [BitsoEntity::class, DetailsEntity::class],
    version = DATABASE_VERSION,
    exportSchema = false
)
@TypeConverters(
    TickerConverter::class,
    BookConverter::class
)
abstract class DatabaseApp : RoomDatabase() {
    abstract fun dao(): DatabaseDao
}