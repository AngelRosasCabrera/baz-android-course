package com.example.bitsocurrency.di

import android.content.Context
import androidx.room.Room
import com.example.bitsocurrency.BuildConfig
import com.example.bitsocurrency.data.database.DatabaseApp
import com.example.bitsocurrency.data.datasource.BitsoDataSource
import com.example.bitsocurrency.data.datasource.BitsoLocalDataSource
import com.example.bitsocurrency.data.datasource.LocalBitsoDataSource
import com.example.bitsocurrency.data.datasource.RemoteBitsoDataSource
import com.example.bitsocurrency.data.repository.BitsoRepository
import com.example.bitsocurrency.data.repository.BitsoRepositoryImpl
import com.example.bitsocurrency.data.services.BitsoService
import com.example.bitsocurrency.data.services.IconService
import com.example.bitsocurrency.utils.constants.Constants.DATABASE_NAME
import com.example.bitsocurrency.utils.network.NetworkUtils
import dagger.Binds
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
abstract class BitsoModule {

    @Binds
    @Singleton
    abstract fun provideBitsoDataSource(remoteBitsoDataSource: RemoteBitsoDataSource): BitsoDataSource

    @Binds
    @Singleton
    abstract fun provideBitsoLocalDataSource(localBitsoDataSource: LocalBitsoDataSource): BitsoLocalDataSource

    @Binds
    @Singleton
    abstract fun provideBitsoRepository(bitsoRepositoryImpl: BitsoRepositoryImpl): BitsoRepository

    @Binds
    @Singleton
    abstract fun provideApplicationContext(@ApplicationContext context: Context): Context


    companion object {
        @Provides
        @Singleton
        fun provideCoroutineDispatcher(): CoroutineDispatcher = Dispatchers.IO

        @Provides
        @Singleton
        fun provideOkHttpClient(): OkHttpClient {
            val interceptor = HttpLoggingInterceptor()
            interceptor.level = HttpLoggingInterceptor.Level.BODY
            return OkHttpClient().newBuilder().addInterceptor(interceptor).addInterceptor { chain ->
                val original = chain.request()
                val request = original.newBuilder()
                    .addHeader("User-Agent", "BitsoCurrency")
                    .method(original.method, original.body)
                    .build()
                chain.proceed(request)

            }.build()
        }

        @Provides
        @Singleton
        fun provideRetrofit(url: String, client: OkHttpClient): Retrofit = Retrofit.Builder()
            .baseUrl(url)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .build()


        @Provides
        @Singleton
        fun provideBitsoService(): BitsoService = provideRetrofit(
            BuildConfig.API_URL,
            provideOkHttpClient()
        ).create(BitsoService::class.java)

        @Provides
        @Singleton
        fun provideIconService(): IconService = provideRetrofit(
            BuildConfig.GITHUB_RAW,
            provideOkHttpClient()
        ).create(IconService::class.java)

        @Provides
        @Singleton
        fun provideDatabaseApp(@ApplicationContext context: Context): DatabaseApp =
            Room.databaseBuilder(context, DatabaseApp::class.java, DATABASE_NAME)
                .fallbackToDestructiveMigration()
                .allowMainThreadQueries()
                .build()

        @Provides
        @Singleton
        fun provideDatabaseDao(databaseApp: DatabaseApp) = databaseApp.dao()
    }
}
