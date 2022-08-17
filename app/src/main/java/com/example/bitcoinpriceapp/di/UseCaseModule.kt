package com.example.bitcoinpriceapp.di

import com.example.bitcoinpriceapp.domain.BitcoinRepository
import com.example.bitcoinpriceapp.domain.FetchBitcoinPricesUsecase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {

    @Singleton
    @Provides
    fun provideUsecase(repository:BitcoinRepository):FetchBitcoinPricesUsecase{
        return FetchBitcoinPricesUsecase(
            repository
        )
    }
}