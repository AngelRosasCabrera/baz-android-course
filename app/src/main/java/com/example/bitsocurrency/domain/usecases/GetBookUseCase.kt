package com.example.bitsocurrency.domain.usecases

import com.example.bitsocurrency.data.repository.BitsoRepository
import com.example.bitsocurrency.domain.models.Book
import io.reactivex.rxjava3.core.Observable
import javax.inject.Inject

class GetBookUseCase  @Inject constructor(private val repository: BitsoRepository)  {
    operator fun invoke(book: String): Observable<Book>{
        return  repository.getBook(book)
    }
}