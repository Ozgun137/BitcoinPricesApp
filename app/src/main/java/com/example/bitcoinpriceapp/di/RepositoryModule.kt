package com.example.bitcoinpriceapp.di

import com.example.bitcoinpriceapp.data.remote.BitcoinRepositoryImpl
import com.example.bitcoinpriceapp.domain.BitcoinRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Singleton
    @Binds
    abstract fun provideBitcoinRepository(bitcoinRepositoryImpl: BitcoinRepositoryImpl):BitcoinRepository
}