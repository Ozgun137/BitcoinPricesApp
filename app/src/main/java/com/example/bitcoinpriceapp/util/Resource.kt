package com.example.bitcoinpriceapp.util

sealed class Resource <T> (data:T? = null, message:String? = null) {
    data class Success<T>(val data:T?):Resource<T>(data)
    class Error<T>(message: String,data:T? = null):Resource<T>(data,message)
    class Loading<T>(val isLoading:Boolean = true):Resource<T>(null)
}