package com.example.bitcoinpriceapp.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.bitcoinpriceapp.databinding.FragmentBitcoinPricesBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch


@AndroidEntryPoint
class BitcoinPricesFragment : Fragment() {


    private lateinit var binding:FragmentBitcoinPricesBinding
    private val viewModel:BitcoinPricesViewModel by viewModels()
    private lateinit var bitcoinPricesAdapter:BitcoinPricesAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentBitcoinPricesBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
        initObservers()
        getBitcoinPrices()
    }

    private fun initObservers() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED){
                    viewModel.bitcoinPriceListState.collectLatest{response->
                        response?.let {
                            bitcoinPricesAdapter.updateBitcoinPricesData(it)
                        }
                    }

            }
        }
    }

    private fun initViews() {
        bitcoinPricesAdapter = BitcoinPricesAdapter()
        binding.bitcoinPricesRv.apply {
            adapter = bitcoinPricesAdapter
            layoutManager = LinearLayoutManager(activity)
        }
    }

    private fun getBitcoinPrices() {
       lifecycleScope.launch {
           while(true){
               viewModel.getBitcoinPrices()
               delay(60000)
           }
       }
    }


}