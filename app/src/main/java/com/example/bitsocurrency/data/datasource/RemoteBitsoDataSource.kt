package com.example.bitsocurrency.data.datasource

import com.example.bitsocurrency.data.services.BitsoService
import com.example.bitsocurrency.data.services.IconService
import com.example.bitsocurrency.data.services.models.bitso.BitsoResponse
import com.example.bitsocurrency.data.services.models.bitso.BookResponse
import com.example.bitsocurrency.data.services.models.bitso.TickerResponse
import com.example.bitsocurrency.data.services.models.icon.IconResponse
import io.reactivex.rxjava3.core.Observable
import javax.inject.Inject

class RemoteBitsoDataSource @Inject constructor(
    private val bitsoService: BitsoService,
    private val iconService: IconService
) : BitsoDataSource {
    override suspend fun getAvailableBooks(): BitsoResponse = bitsoService.getAvailableBooks()

    override fun getTicker(book: String): Observable<TickerResponse> = bitsoService.getTicker(book)

    override fun getBook(book: String): Observable<BookResponse> = bitsoService.getBook(book)

    override suspend fun getMapIcons(): IconResponse = iconService.getMapIcons()
}