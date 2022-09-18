package com.example.bitsocurrency.domain.usecases

import com.example.bitsocurrency.data.repository.BitsoRepository
import com.example.bitsocurrency.data.repository.CoinRepository
import com.example.bitsocurrency.domain.models.Bitso
import javax.inject.Inject

class AvailableBooksUseCase @Inject constructor(
    private val repository: BitsoRepository,
    private val coinRepository: CoinRepository
) {
    private var content: List<Bitso> = mutableListOf()

    suspend operator fun invoke(isRefresh: Boolean): List<Bitso> {
        return if (isRefresh) fillList() else content.ifEmpty { fillList() }
    }

    private suspend fun fillList(): List<Bitso> {
        val result = repository.getAvailableBooks()
        content = result.ifEmpty { content }
        return content
    }
}