package com.example.bitsocurrency.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bitsocurrency.domain.models.Bitso
import com.example.bitsocurrency.domain.models.Book
import com.example.bitsocurrency.domain.models.Ticker
import com.example.bitsocurrency.domain.usecases.AvailableBooksUseCase
import com.example.bitsocurrency.domain.usecases.GetDetailsUseCase
import com.example.bitsocurrency.utils.network.NetworkUtils
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject
import kotlinx.coroutines.launch

@HiltViewModel
class BitsoViewModel @Inject constructor(
    private val availableBooksUseCase: AvailableBooksUseCase,
    private val detailsUseCase: GetDetailsUseCase,
    private val networkUtils: NetworkUtils
) : ViewModel() {

    private val _availableBooks = MutableLiveData<List<Bitso>>()
    val availableBooks: LiveData<List<Bitso>> get() = _availableBooks

    private val _books = MutableLiveData<Book>()
    val books: LiveData<Book> get() = _books

    private val _tickers = MutableLiveData<Ticker>()
    val tickers: LiveData<Ticker> get() = _tickers

    private val _loading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean> = _loading

    private val _wifiOff = MutableLiveData<Boolean>()
    val wifiOff: LiveData<Boolean> = _wifiOff

    private val _messageError = MutableLiveData<String>()
    val messageError: LiveData<String> = _messageError

    private var compositeDisposable: CompositeDisposable? = CompositeDisposable()

    fun getAvailableBooks(isRefresh: Boolean) = viewModelScope.launch {
        try {
            _wifiOff.value = networkUtils.isNetworkAvailable()
            _loading.value = true
            _availableBooks.value = availableBooksUseCase.invoke(isRefresh)
            _loading.value = false
        } catch (e: java.lang.Exception) {
            _messageError.value = "Ha ocurrido un error."
        }
    }

    fun getDetails(detailId: Int, book: String) {
        _loading.value = true
        _wifiOff.value = networkUtils.isNetworkConnected()
        detailsUseCase.invoke(detailId, book)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { disposable -> compositeDisposable?.add(disposable) }
            .doOnError { onError -> _messageError.value = onError.message }
            .doOnSuccess { item ->
                _books.value = item.book
                _tickers.value = item.tickers
                _loading.value = false
                _wifiOff.value = networkUtils.isNetworkConnected()
            }
            .subscribe()
    }

    private fun unSubscribeFromObservable() {
        if (compositeDisposable != null && !compositeDisposable?.isDisposed!!) {
            compositeDisposable?.dispose()
        }
    }

    override fun onCleared() {
        super.onCleared()
        unSubscribeFromObservable()
        compositeDisposable?.clear()
    }

}