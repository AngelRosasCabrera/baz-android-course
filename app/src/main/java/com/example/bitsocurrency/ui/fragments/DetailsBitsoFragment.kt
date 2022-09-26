package com.example.bitsocurrency.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.navArgs
import com.example.bitsocurrency.databinding.FragmentDetailsBitsoBinding
import com.example.bitsocurrency.ui.activities.MainActivity
import com.example.bitsocurrency.ui.adapters.BitsoTabAdapter
import com.example.bitsocurrency.ui.viewmodel.BitsoViewModel
import com.example.bitsocurrency.utils.extensions.formatAsCurrency
import com.google.android.material.tabs.TabLayoutMediator

class DetailsBitsoFragment: Fragment() {

    private val viewModel: BitsoViewModel by activityViewModels()
    private lateinit var binding: FragmentDetailsBitsoBinding
    private val args: DetailsBitsoFragmentArgs by navArgs()
    private lateinit var bitsoTabAdapter: BitsoTabAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentDetailsBitsoBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val data = args.bitso

        with(binding.clDetailsBitso) {
            tvDetailsCurrencyName.text = data.name
            tvDetailsCurrencySymbol.text = data.symbol
        }

        viewModel.getDetails(data.book)

        viewModel.tickers.observe(viewLifecycleOwner) {
            with(binding.clDetailsBitso) {
                tvDetailsMaximumPrice.text = it.high.toDouble().formatAsCurrency()
                tvDetailsMinimumPrice.text = it.low.toDouble().formatAsCurrency()
                tvDetailsLastPrice.text = it.last.toDouble().formatAsCurrency()
            }
        }
        bitsoTabAdapter = BitsoTabAdapter(this)
        binding.pager.adapter = bitsoTabAdapter
        TabLayoutMediator(binding.tabLayout, binding.pager) { tab, position ->
            tab.text = if (position == 0) "Ask" else "Bids"
        }.attach()

//        binding.btnBack.setOnClickListener { findNavController().popBackStack() }
    }

    fun hideLoading() = (requireActivity() as MainActivity).hideLoading()

    fun showLoading() = (requireActivity() as MainActivity).showLoading()

    override fun onDestroy() {
        viewModel.onDestroy()
        super.onDestroy()
    }

}