package com.example.bitsocurrency.data.repository

import com.example.bitsocurrency.data.datasource.BitsoDataSource
import com.example.bitsocurrency.data.datasource.BitsoLocalDataSource
import com.example.bitsocurrency.data.mappers.toDatabase
import com.example.bitsocurrency.data.mappers.toDomain
import com.example.bitsocurrency.data.services.models.icon.IconResponseItem
import com.example.bitsocurrency.domain.models.Bitso
import com.example.bitsocurrency.domain.models.Book
import com.example.bitsocurrency.domain.models.Ticker
import com.example.bitsocurrency.domain.models.toDomain
import com.example.bitsocurrency.utils.constants.Constants.DEFAULT_ICON
import com.example.bitsocurrency.utils.constants.Constants.DELIMITER_UNDESCORE
import com.example.bitsocurrency.utils.network.NetworkUtils
import io.reactivex.rxjava3.core.Observable
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class BitsoRepositoryImpl @Inject constructor(
    private val dataSource: BitsoDataSource,
    private val localSource: BitsoLocalDataSource,
    private val dispatcher: CoroutineDispatcher,
) : BitsoRepository {
    override suspend fun getAvailableBooks(): List<Bitso> = withContext(dispatcher) {
        if (NetworkUtils.isNetworkAvailable()) {
            localSource.deleteAllData()
            val mapOfIcons = dataSource.getMapIcons()
            val data = dataSource.getAvailableBooks().payload.map {
                val coin = it.book.split(DELIMITER_UNDESCORE).first()
                val icons: List<IconResponseItem> =
                    mapOfIcons.filter { item -> item.symbol.lowercase() == coin.lowercase() }
                val icon = icons.firstOrNull()
                val defaultIcon =
                    IconResponseItem(imgUrl = DEFAULT_ICON, name = coin.uppercase(), symbol = coin)
                it.toDatabase(icon ?: defaultIcon)
            }
            localSource.insertData(data)
        }
        localSource.getAllData().map { it.toDomain() }
    }

    override fun getTicker(book: String): Observable<Ticker> {
        return dataSource.getTicker(book).map { it.payload.toDomain() }
    }

    override fun getBook(book: String): Observable<Book> {
        return dataSource.getBook(book).map { it.payload.toDomain() }
    }

}