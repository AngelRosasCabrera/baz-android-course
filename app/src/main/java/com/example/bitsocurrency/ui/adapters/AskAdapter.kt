package com.example.bitsocurrency.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.bitsocurrency.databinding.ListAskBidBinding
import com.example.bitsocurrency.domain.models.Ask

class AskAdapter : ListAdapter<Ask, AskAdapter.AskBidAdapter>(DiffCallback) {

    object DiffCallback : DiffUtil.ItemCallback<Ask>() {
        override fun areItemsTheSame(oldItem: Ask, newItem: Ask): Boolean {
            return oldItem.book == newItem.book
        }

        override fun areContentsTheSame(oldItem: Ask, newItem: Ask): Boolean {
            return oldItem == newItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AskBidAdapter {
        val binding = ListAskBidBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return AskBidAdapter(binding)
    }

    override fun onBindViewHolder(holder: AskBidAdapter, position: Int) = holder.bind()

    inner class AskBidAdapter(private val binding: ListAskBidBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind() {
            with(binding){
                val currentItem = getItem(adapterPosition)
                tvAskBidAmount.text = currentItem.amount
                tvAskBidPrice.text = currentItem.price
            }
        }
    }
}