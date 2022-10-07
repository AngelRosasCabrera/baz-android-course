package com.example.bitsocurrency.ui.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.airbnb.lottie.LottieDrawable
import com.example.bitsocurrency.R
import com.example.bitsocurrency.databinding.FragmentDetailsBitsoBinding
import com.example.bitsocurrency.domain.models.Bitso
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
        cardViewDetails(args.bitso)
        setTabLayout()
        onBackPressed()

        wifiObserver()
        loadingObserver()
        messageObserver()

    }

    private fun cardViewDetails(bitso: Bitso) {
        viewModel.getDetails(bitso.bitsoId, bitso.book)
        viewModel.tickers.observe(viewLifecycleOwner) {
            with(binding.clDetailsBitso) {
                tvDetailsCurrencyName.text = bitso.name
                tvDetailsCurrencySymbol.text = bitso.symbol
                tvDetailsMaximumPrice.text = it.high.toDouble().formatAsCurrency()
                tvDetailsMinimumPrice.text = it.low.toDouble().formatAsCurrency()
                tvDetailsLastPrice.text = it.last.toDouble().formatAsCurrency()
            }
        }
    }

    private fun wifiObserver() {
        viewModel.wifiOff.observe(viewLifecycleOwner) { isConnected ->
            val style: Int
            if (isConnected) {
                binding.ltWifiOff.visibility = View.GONE
                style = R.drawable.style_connection_normal
            }
            else {
                binding.ltWifiOff.visibility = View.VISIBLE
                binding.ltWifiOff.playAnimation()
                binding.ltWifiOff.repeatCount = LottieDrawable.INFINITE
                style = R.drawable.style_without_connection_to_internet
            }
            binding.clDetailsBitso.root.background = ContextCompat.getDrawable(requireContext(), style)
        }
    }

    private fun loadingObserver(){
        viewModel.loading.observe(viewLifecycleOwner) { isLoading ->
            val activity = (requireActivity() as MainActivity)
            if(isLoading) activity.showLoading() else activity.hideLoading()
        }
    }

    private fun messageObserver() {
        viewModel.messageError.observe(viewLifecycleOwner) { message ->
            Log.e("AndroidStudio", message)
        }
    }

    private fun setTabLayout() {
        bitsoTabAdapter = BitsoTabAdapter(this)
        binding.pager.adapter = bitsoTabAdapter
        TabLayoutMediator(binding.tabLayout, binding.pager) { tab, position ->
            tab.text = if (position == 0) getString(R.string.tab_ask) else getString(R.string.tab_bid)
        }.attach()
    }

    private fun onBackPressed() {
        val callback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                findNavController().popBackStack()
            }
        }

        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner, callback)
    }

}