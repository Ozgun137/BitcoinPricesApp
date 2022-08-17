package com.example.bitcoinpriceapp.data.remote

import com.example.bitcoinpriceapp.data.remote.dto.BitcoinPricesResponseItem
import com.example.bitcoinpriceapp.util.Constants
import retrofit2.Response
import retrofit2.http.GET


interface BitcoinService {

    @GET(Constants.END_POINT)
    suspend fun getBitcoinPrices():Response<BitcoinPricesResponseItem>
}