package com.example.bitcoinpriceapp.domain

import com.example.bitcoinpriceapp.data.remote.dto.BitcoinPricesResponseItem
import com.example.bitcoinpriceapp.util.Resource
import kotlinx.coroutines.flow.Flow

interface BitcoinRepository {
    fun fetchBitcoinPrices():Flow<Resource<BitcoinPricesResponseItem>>
}