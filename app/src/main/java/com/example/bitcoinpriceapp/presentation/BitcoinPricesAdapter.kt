package com.example.bitcoinpriceapp.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.bitcoinpriceapp.data.remote.dto.BitcoinPricesResponseItem
import com.example.bitcoinpriceapp.databinding.BitcoinPriceItemBinding

class BitcoinPricesAdapter : RecyclerView.Adapter<BitcoinPricesAdapter.BitcoinPricesViewHolder>() {

    private lateinit var binding:BitcoinPriceItemBinding
    private var bitcoinPricesList = mutableListOf<BitcoinPricesResponseItem>()

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BitcoinPricesViewHolder {
        binding = BitcoinPriceItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return BitcoinPricesViewHolder(binding)
    }

    override fun onBindViewHolder(holder: BitcoinPricesViewHolder, position: Int) {
        val bitcoinPriceItem = bitcoinPricesList[position]
        holder.bind(bitcoinPriceItem)
    }

    override fun getItemCount(): Int {
        return bitcoinPricesList.size
    }

    fun updateBitcoinPricesData(prices:List<BitcoinPricesResponseItem>){
        bitcoinPricesList.addAll(prices)
        notifyDataSetChanged()
    }


    inner class BitcoinPricesViewHolder(private val binding:BitcoinPriceItemBinding) : RecyclerView.ViewHolder(binding.root){

        fun bind(bitcoinPrices: BitcoinPricesResponseItem){
           binding.priceText.text = "Bitcoin Price: " + bitcoinPrices.bpi.uSD.rate
           binding.timeStampText.text = "Time : " + bitcoinPrices.time.updated
        }
    }
}