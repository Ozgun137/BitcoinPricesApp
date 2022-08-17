package com.example.bitcoinpriceapp.domain

import com.example.bitcoinpriceapp.data.remote.dto.BitcoinPricesResponseItem
import com.example.bitcoinpriceapp.util.Resource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class FetchBitcoinPricesUsecase @Inject constructor(
    private val repository: BitcoinRepository
) {

     operator fun invoke(): Flow<Resource<BitcoinPricesResponseItem>>
     {
        return repository.fetchBitcoinPrices()
     }
}