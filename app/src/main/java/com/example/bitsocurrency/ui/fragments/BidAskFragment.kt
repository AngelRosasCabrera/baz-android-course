package com.example.bitsocurrency.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.bitsocurrency.databinding.FragmentBidAskBinding
import com.example.bitsocurrency.ui.adapters.AskBidAdapter
import com.example.bitsocurrency.ui.viewmodel.BitsoViewModel

class BidAskFragment(private val ask: Boolean) : Fragment() {

    private val viewModel: BitsoViewModel by activityViewModels()

    private lateinit var binding: FragmentBidAskBinding

    private lateinit var adapter: AskBidAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentBidAskBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter = AskBidAdapter()
        with(binding) {
            rvAskBid.setHasFixedSize(false)
            rvAskBid.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            rvAskBid.adapter = adapter
        }

        viewModel.books.observe(viewLifecycleOwner) {
            if (ask) {
                adapter.submitList(it.asks)
            } else {
                adapter.submitList(it.bids)
            }
        }


    }

    companion object {
        @JvmStatic
        fun newInstance(ask: Boolean = false) = BidAskFragment(ask)
    }
}