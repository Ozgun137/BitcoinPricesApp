package com.example.bitcoinpriceapp.data.remote.dto


import com.google.gson.annotations.SerializedName

data class USD(
    @SerializedName("code")
    val code: String,
    @SerializedName("description")
    val description: String,
    @SerializedName("rate")
    val rate: String,
    @SerializedName("rate_float")
    val rateFloat: Double,
    @SerializedName("symbol")
    val symbol: String
)