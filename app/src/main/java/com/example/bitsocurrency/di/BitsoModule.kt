package com.example.bitsocurrency.di

import com.example.bitsocurrency.BuildConfig
import com.example.bitsocurrency.data.repository.BitsoRepository
import com.example.bitsocurrency.data.repository.BitsoRepositoryImpl
import com.example.bitsocurrency.data.services.BitsoService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class BitsoModule {

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BASIC
        return OkHttpClient().newBuilder().addInterceptor(interceptor).build()
    }


    @Provides
    @Singleton
    fun provideRetrofit(client: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.API_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }


    @Provides
    @Singleton
    fun provideBitsoService(): BitsoService {
        return provideRetrofit(provideOkHttpClient()).create(BitsoService::class.java)
    }

    @Provides
    @Singleton
    fun provideBitsoRepository(): BitsoRepository {
        return BitsoRepositoryImpl(provideBitsoService())
    }
}