package com.example.bitsocurrency.domain.usecases

import com.example.bitsocurrency.data.repository.BitsoRepository
import com.example.bitsocurrency.domain.models.Bitso
import javax.inject.Inject

class AvailableBooksUseCase @Inject constructor(private val repository: BitsoRepository) {
    private var content: List<Bitso> = mutableListOf()

    suspend operator fun invoke(isRefresh: Boolean): List<Bitso> {
        if (isRefresh) content = repository.getAvailableBooks()

        return content.ifEmpty { repository.getAvailableBooks() }
    }
}