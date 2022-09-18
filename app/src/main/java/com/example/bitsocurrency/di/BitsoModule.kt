package com.example.bitsocurrency.di

import com.example.bitsocurrency.BuildConfig.API_COIN_MARKET
import com.example.bitsocurrency.BuildConfig.API_URL
import com.example.bitsocurrency.data.repository.BitsoRepository
import com.example.bitsocurrency.data.repository.BitsoRepositoryImpl
import com.example.bitsocurrency.data.repository.CoinRepository
import com.example.bitsocurrency.data.repository.CoinRepositoryImpl
import com.example.bitsocurrency.data.services.BitsoService
import com.example.bitsocurrency.data.services.CoinService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class BitsoModule {

    @Provides
    @Singleton
    fun provideCoroutineDispatcher(): CoroutineDispatcher = Dispatchers.IO

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        return OkHttpClient().newBuilder().addInterceptor(interceptor).build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(url: String, client: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(url)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideBitsoService(): BitsoService {
        return provideRetrofit(API_URL, provideOkHttpClient()).create(BitsoService::class.java)
    }

    @Provides
    @Singleton
    fun provideBitsoRepository(): BitsoRepository {
        return BitsoRepositoryImpl(provideBitsoService(), provideCoroutineDispatcher())
    }

    @Provides
    @Singleton
    fun provideCoinService(): CoinService{
        return provideRetrofit(API_COIN_MARKET, provideOkHttpClient()).create(CoinService::class.java)
    }

    @Provides
    @Singleton
    fun provideCoinRepository(): CoinRepository {
        return CoinRepositoryImpl(provideCoinService(), provideCoroutineDispatcher())
    }
}