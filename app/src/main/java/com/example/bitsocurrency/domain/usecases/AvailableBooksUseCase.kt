package com.example.bitsocurrency.domain.usecases

import com.example.bitsocurrency.data.repository.BitsoRepository
import com.example.bitsocurrency.data.repository.DatabaseRepository
import com.example.bitsocurrency.domain.models.Bitso
import javax.inject.Inject

class AvailableBooksUseCase @Inject constructor(
    private val repository: BitsoRepository,
    private val databaseRepository: DatabaseRepository
) {
    private var content: List<Bitso> = mutableListOf()

    suspend operator fun invoke(isRefresh: Boolean): List<Bitso> {
        return if (isRefresh) fillList() else content.ifEmpty { databaseRepository.getAllData() }
    }

    private suspend fun fillList(): List<Bitso> {
        databaseRepository.deleteAllData()
        content = repository.getAvailableBooks()
        databaseRepository.insertData(content)
        return content
    }
}