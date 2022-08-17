package com.example.bitcoinpriceapp.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bitcoinpriceapp.data.remote.dto.BitcoinPricesResponseItem
import com.example.bitcoinpriceapp.domain.FetchBitcoinPricesUsecase
import com.example.bitcoinpriceapp.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import javax.inject.Inject

@HiltViewModel
class BitcoinPricesViewModel @Inject constructor(
    private val fetchBitcoinPricesUsecase: FetchBitcoinPricesUsecase
) : ViewModel() {

    private val _bitcoinPriceListState = MutableStateFlow<List<BitcoinPricesResponseItem>?>(emptyList())
    val bitcoinPriceListState = _bitcoinPriceListState.asStateFlow()

    private val bitcoinPricesList = mutableListOf<BitcoinPricesResponseItem>()
    private var job: Job? = null


    fun getBitcoinPrices() {
        job?.cancel()
        job = null
        job = viewModelScope.launch{
            while(true){
                fetchBitcoinPricesUsecase().collectLatest{ bitcoinPrices->
                    when(bitcoinPrices){
                        is Resource.Success-> {
                            bitcoinPrices.data?.let { bitcoinPricesList.add(it) }
                            _bitcoinPriceListState.value = bitcoinPricesList
                        }
                        else -> {}
                    }

                }
                delay(5000)
            }

        }
    }


}