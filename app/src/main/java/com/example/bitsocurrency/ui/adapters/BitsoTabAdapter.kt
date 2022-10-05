package com.example.bitsocurrency.ui.adapters

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.bitsocurrency.ui.fragments.BidAskFragment

class BitsoTabAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {
    override fun getItemCount(): Int = 2

    override fun createFragment(position: Int): Fragment {
        return if (position == 0) BidAskFragment.newInstance(true)
        else BidAskFragment.newInstance()

    }
}