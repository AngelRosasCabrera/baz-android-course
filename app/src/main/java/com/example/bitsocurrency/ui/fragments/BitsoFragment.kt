package com.example.bitsocurrency.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.navGraphViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.bitsocurrency.R
import com.example.bitsocurrency.databinding.FragmentBitsoBinding
import com.example.bitsocurrency.domain.models.Bitso
import com.example.bitsocurrency.ui.activities.MainActivity
import com.example.bitsocurrency.ui.adapters.BitsoAdapter
import com.example.bitsocurrency.ui.viewmodel.BitsoViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BitsoFragment : Fragment(), BitsoAdapter.BitsoOnItemClickListener {

    private val viewModel: BitsoViewModel by activityViewModels()
    private lateinit var binding: FragmentBitsoBinding
    private lateinit var adapter: BitsoAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentBitsoBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = BitsoAdapter(this, requireContext())

        with(binding) {
            rvBitsoCurrency.adapter = adapter
            rvBitsoCurrency.setHasFixedSize(false)
            rvBitsoCurrency.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            swBitsoRefresh.setOnRefreshListener {
                swBitsoRefresh.isRefreshing = true
                viewModel.getAvailableBooks(isRefresh = true)
            }
        }
        viewModel.getAvailableBooks(isRefresh = false)

        viewModel.availableBooks.observe(viewLifecycleOwner) {
            adapter.submitList(it)
            (requireActivity() as MainActivity).hideLoading()
            binding.swBitsoRefresh.isRefreshing = false
        }
    }

    override fun onItemClickListener(bitso: Bitso) {
        val action = BitsoFragmentDirections.actionNavigationHomeBitsoToNavigationDetailsBitso(bitso)
        findNavController().navigate(action)
    }

}