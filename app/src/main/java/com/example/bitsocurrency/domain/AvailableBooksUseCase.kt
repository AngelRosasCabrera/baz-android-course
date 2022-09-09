package com.example.bitsocurrency.domain

import com.example.bitsocurrency.data.repository.BitsoRepository
import com.example.bitsocurrency.domain.models.Bitso
import javax.inject.Inject

class AvailableBooksUseCase @Inject constructor(private val repository: BitsoRepository) {
    suspend operator fun invoke(): List<Bitso> {
        return repository.getAvailableBooks()
    }
}