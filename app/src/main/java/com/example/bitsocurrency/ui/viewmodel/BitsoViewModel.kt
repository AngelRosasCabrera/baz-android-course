package com.example.bitsocurrency.ui.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bitsocurrency.domain.models.Bitso
import com.example.bitsocurrency.domain.models.Book
import com.example.bitsocurrency.domain.models.Ticker
import com.example.bitsocurrency.domain.usecases.AvailableBooksUseCase
import com.example.bitsocurrency.domain.usecases.GetDetailsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BitsoViewModel @Inject constructor(
    private val availableBooksUseCase: AvailableBooksUseCase,
    private val detailsUseCase: GetDetailsUseCase
) : ViewModel() {

    private val _availableBooks = MutableLiveData<List<Bitso>>()
    val availableBooks: LiveData<List<Bitso>> get() = _availableBooks

    private val _books = MutableLiveData<Book>()
    val books: LiveData<Book> get() = _books

    private val _tickers = MutableLiveData<Ticker>()
    val tickers: LiveData<Ticker> get() = _tickers

    private var compositeDisposable: CompositeDisposable? = CompositeDisposable()

    fun getAvailableBooks(isRefresh: Boolean) = viewModelScope.launch {
        _availableBooks.value = availableBooksUseCase.invoke(isRefresh)
    }

    fun getDetails(book: String) {
        detailsUseCase.invoke(book)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { disposable -> compositeDisposable?.add(disposable) }
            .doOnError { onError -> Log.d("AndroidStudio", onError.message.toString()) }
            .doOnNext { item ->
                _books.value = item.book
                _tickers.value = item.tickers
            }
            .doOnComplete { Log.d("AndroidStudio", "Completo") }
            .subscribe()
    }

    private fun unSubscribeFromObservable() {
        if (compositeDisposable != null && !compositeDisposable?.isDisposed!!) {
            compositeDisposable?.dispose()
        }
    }

    fun onDestroy() {
        unSubscribeFromObservable()
        compositeDisposable = null
    }

}