package com.example.bitsocurrency.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bitsocurrency.domain.models.Bitso
import com.example.bitsocurrency.domain.usecases.AvailableBooksUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BitsoViewModel @Inject constructor(private val availableBooksUseCase: AvailableBooksUseCase) :
    ViewModel() {

    private val _availableBooks = MutableLiveData<List<Bitso>>()
    val availableBooks: LiveData<List<Bitso>> get() = _availableBooks


    fun getAvailableBooks(isRefresh: Boolean) = viewModelScope.launch {
        _availableBooks.value = availableBooksUseCase.invoke(isRefresh)
    }

}