package com.example.bitsocurrency.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.bitsocurrency.databinding.FragmentDetailsBitsoBinding
import com.example.bitsocurrency.ui.viewmodel.BitsoViewModel

class DetailsBitsoFragment: Fragment() {

    private val viewModel: BitsoViewModel by activityViewModels()
    private lateinit var binding: FragmentDetailsBitsoBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentDetailsBitsoBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

}