package com.example.bitsocurrency.domain.usecases

import com.example.bitsocurrency.data.repository.BitsoRepository
import com.example.bitsocurrency.domain.models.Details
import io.reactivex.rxjava3.core.Observable
import javax.inject.Inject

class GetDetailsUseCase @Inject constructor(private val repository: BitsoRepository) {

    operator fun invoke(book: String): Observable<Details> {
        return Observable.zip(
            repository.getTicker(book),
            repository.getBook(book)
        ) { tickers, books -> Details(tickers, books) }
    }

}