package com.example.bitsocurrency.data.repository

import com.example.bitsocurrency.data.database.entities.BitsoEntity
import com.example.bitsocurrency.data.datasource.BitsoDataSource
import com.example.bitsocurrency.data.datasource.BitsoLocalDataSource
import com.example.bitsocurrency.data.mappers.toDatabase
import com.example.bitsocurrency.data.mappers.toDomain
import com.example.bitsocurrency.data.services.models.bitso.BitsoModel
import com.example.bitsocurrency.data.services.models.icon.IconResponse
import com.example.bitsocurrency.data.services.models.icon.IconResponseItem
import com.example.bitsocurrency.domain.models.Bitso
import com.example.bitsocurrency.domain.models.Book
import com.example.bitsocurrency.domain.models.Details
import com.example.bitsocurrency.domain.models.Ticker
import com.example.bitsocurrency.domain.models.toDomain
import com.example.bitsocurrency.utils.constants.Constants.DEFAULT_ICON
import com.example.bitsocurrency.utils.constants.Constants.DELIMITER_UNDESCORE
import com.example.bitsocurrency.utils.network.NetworkUtils
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

class BitsoRepositoryImpl @Inject constructor(
    private val dataSource: BitsoDataSource,
    private val localSource: BitsoLocalDataSource,
    private val dispatcher: CoroutineDispatcher,
    private val networkUtils: NetworkUtils
) : BitsoRepository {
    override suspend fun getAvailableBooks(): List<Bitso> = withContext(dispatcher) {
        if (networkUtils.isNetworkAvailable()) {
            localSource.deleteAllData()
            val iconResponse = dataSource.getMapIcons()
            val bitsoCurrency = dataSource.getAvailableBooks().payload.map { bitso-> bitso.toDatabase(icon(bitso, iconResponse)) }
            localSource.insertData(bitsoCurrency)
        }
        localSource.getAllData().map(BitsoEntity::toDomain)
    }

    override fun getTicker(book: String): Single<Ticker> {
        return if (networkUtils.isNetworkConnected())
            dataSource.getTicker(book).map { it.payload.toDomain() }
        else Single.never()
    }

    override fun getBook(book: String): Single<Book> {
        return if (networkUtils.isNetworkConnected())
            dataSource.getBook(book).map { it.payload.toDomain() }
        else Single.never()
    }

    override fun insertDetails(data: Details) = localSource.insertDetails(data.toDatabase())

    override fun getDetails(bitsoId: Int): Details = localSource.getAllDetails(bitsoId).toDomain()

    override fun deleteAllDetails() = localSource.deleteAllDetails()

    private fun icon(bitso: BitsoModel, iconResponse: IconResponse): IconResponseItem {
        val coin = bitso.book.split(DELIMITER_UNDESCORE).first()
        val icons: List<IconResponseItem> = iconResponse.filter { item -> item.symbol.lowercase() == coin.lowercase() }
        val icon = icons.firstOrNull()
        val defaultIcon = IconResponseItem(imgUrl = DEFAULT_ICON, name = coin.uppercase(), symbol = coin)
        return icon ?: defaultIcon
    }

}