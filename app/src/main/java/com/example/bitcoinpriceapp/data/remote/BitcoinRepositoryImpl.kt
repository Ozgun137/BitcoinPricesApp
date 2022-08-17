package com.example.bitcoinpriceapp.data.remote

import com.example.bitcoinpriceapp.data.remote.dto.BitcoinPricesResponseItem
import com.example.bitcoinpriceapp.domain.BitcoinRepository
import com.example.bitcoinpriceapp.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class BitcoinRepositoryImpl @Inject constructor(
    private val bitcoinService: BitcoinService
): BitcoinRepository {


    override fun fetchBitcoinPrices(): Flow<Resource<BitcoinPricesResponseItem>> = flow{
        emit(Resource.Loading(true))
        try {
            var response = bitcoinService.getBitcoinPrices()
            response.body()?.let {
                emit(Resource.Success(it))
            }
        }

        catch (exception:IOException){
             emit(Resource.Error(exception.toString()))
        }

        catch (exception:HttpException){
            emit(Resource.Error(exception.toString()))
        }
    }
}