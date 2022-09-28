package com.example.bitsocurrency.domain.usecases

import com.example.bitsocurrency.data.repository.BitsoRepository
import com.example.bitsocurrency.domain.models.Details
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class GetDetailsUseCase @Inject constructor(private val repository: BitsoRepository) {

    operator fun invoke(book: String): Single<Details> {
        return Single.zip(repository.getTicker(book), repository.getBook(book)) { tickers, books ->
            Details(tickers, books)
        }
    }

}