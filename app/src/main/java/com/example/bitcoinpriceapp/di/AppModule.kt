package com.example.bitcoinpriceapp.di

import android.app.Application
import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.example.bitcoinpriceapp.data.remote.BitcoinService
import com.example.bitcoinpriceapp.util.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideBitcoinService(application:Application):BitcoinService{
        val client = OkHttpClient.Builder()
            .addInterceptor(ChuckerInterceptor(application))
            .build()

        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
            .create()
    }
}