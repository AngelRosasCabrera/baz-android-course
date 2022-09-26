package com.example.bitsocurrency.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.bitsocurrency.databinding.ListAskBidBinding
import com.example.bitsocurrency.domain.models.Bid

class BidAdapter : ListAdapter<Bid, BidAdapter.BidBidAdapter>(DiffCallback) {

    object DiffCallback : DiffUtil.ItemCallback<Bid>() {
        override fun areItemsTheSame(oldItem: Bid, newItem: Bid): Boolean {
            return oldItem.book == newItem.book
        }

        override fun areContentsTheSame(oldItem: Bid, newItem: Bid): Boolean {
            return oldItem == newItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BidBidAdapter {
        val binding = ListAskBidBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return BidBidAdapter(binding)
    }

    override fun onBindViewHolder(holder: BidBidAdapter, position: Int) = holder.bind()

    inner class BidBidAdapter(private val binding: ListAskBidBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind() {
            with(binding){
                val currentItem = getItem(adapterPosition)
                tvAskBidAmount.text = currentItem.amount
                tvAskBidPrice.text = currentItem.price
            }
        }
    }
}