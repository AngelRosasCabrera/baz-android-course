package com.example.bitsocurrency.di

import android.content.Context
import androidx.room.Room
import com.example.bitsocurrency.BuildConfig.API_COIN_MARKET
import com.example.bitsocurrency.BuildConfig.API_URL
import com.example.bitsocurrency.data.database.DatabaseApp
import com.example.bitsocurrency.data.database.DatabaseDao
import com.example.bitsocurrency.data.repository.BitsoRepository
import com.example.bitsocurrency.data.repository.BitsoRepositoryImpl
import com.example.bitsocurrency.data.repository.CoinRepository
import com.example.bitsocurrency.data.repository.CoinRepositoryImpl
import com.example.bitsocurrency.data.services.BitsoService
import com.example.bitsocurrency.data.services.CoinService
import com.example.bitsocurrency.utils.constants.Constants.DATABASE_NAME
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
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

    // region CoroutineDispatcher
    @Provides
    @Singleton
    fun provideCoroutineDispatcher(): CoroutineDispatcher = Dispatchers.IO
    // endregion

    // region Clients http
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
    // endregion

    // region Bitso
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

    // endregion

    // region Coin
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

    // endregion

    // region Database
    @Provides
    @Singleton
    fun provideDatabaseApp(@ApplicationContext context: Context): DatabaseApp =
        Room.databaseBuilder(context, DatabaseApp::class.java, DATABASE_NAME)
            .fallbackToDestructiveMigration()
            .allowMainThreadQueries()
            .build()

    @Provides
    @Singleton
    fun provideDatabaseDao(@ApplicationContext context: Context): DatabaseDao =
        provideDatabaseApp(context).dao()
    // endregion
}