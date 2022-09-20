package com.example.bitsocurrency.data.repository

import com.example.bitsocurrency.data.services.BitsoService
import com.example.bitsocurrency.data.services.models.bitso.BookModel
import com.example.bitsocurrency.data.services.models.bitso.TickerModel
import com.example.bitsocurrency.data.services.models.icon.IconResponseItem
import com.example.bitsocurrency.domain.models.Bitso
import com.example.bitsocurrency.utils.constants.Constants.DEFAULT_ICON
import com.example.bitsocurrency.utils.constants.Constants.DELIMITER_UNDESCORE
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class BitsoRepositoryImpl @Inject constructor(
    private val service: BitsoService,
    private val dispatcher: CoroutineDispatcher,
    private val iconRepository: IconRepository
) : BitsoRepository {
    override suspend fun getAvailableBooks(): List<Bitso> = withContext(dispatcher) {
        val mapOfIcons = iconRepository.getMapIcons()
        return@withContext service.getAvailableBooks().payload.map {
            val coin = it.book.split(DELIMITER_UNDESCORE)[0]
            val icons = mapOfIcons.filter { item -> item.symbol.lowercase() == coin.lowercase() }
            val icon = if (icons.isEmpty())
                IconResponseItem(
                    imgUrl = DEFAULT_ICON,
                    name = coin.uppercase(),
                    symbol = coin
                )
            else icons[0]
            Bitso(
                book = it.book,
                defaultChart = it.defaultChart,
                maximumAmount = it.maximumAmount,
                maximumPrice = it.maximumPrice,
                maximumValue = it.maximumValue,
                minimumAmount = it.minimumAmount,
                minimumPrice = it.minimumPrice,
                minimumValue = it.minimumValue,
                tickSize = it.tickSize,
                imgUrl = icon.imgUrl,
                name = icon.name,
                symbol = icon.symbol,
            )
        }
    }


    override suspend fun getTicker(): TickerModel {
        TODO("Not yet implemented")
    }

    override suspend fun getBook(book: String): BookModel {
        TODO("Not yet implemented")
    }

}