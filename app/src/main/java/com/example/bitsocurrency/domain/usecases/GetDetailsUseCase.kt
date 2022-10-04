package com.example.bitsocurrency.domain.usecases

import com.example.bitsocurrency.data.repository.BitsoRepository
import com.example.bitsocurrency.domain.models.Details
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class GetDetailsUseCase @Inject constructor(private val repository: BitsoRepository) {

    operator fun invoke(bitsoId: Int, book: String): Single<Details> {
        return Single.zip(repository.getTicker(book), repository.getBook(book)) { tickers, books ->
            val details = Details(bitsoId = bitsoId,tickers = tickers, book = books)
            if (details.tickers.ask.isNotEmpty()) {
                repository.deleteAllDetails()
                repository.insertDetails(details)
            }
            repository.getDetails(bitsoId)
        }
    }

}