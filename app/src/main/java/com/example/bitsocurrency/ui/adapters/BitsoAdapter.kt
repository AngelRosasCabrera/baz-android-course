package com.example.bitsocurrency.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.bitsocurrency.databinding.ListItemBitsoBinding
import com.example.bitsocurrency.domain.models.Bitso
import com.example.bitsocurrency.utils.extensions.loadUrl

class BitsoAdapter : ListAdapter<Bitso, BitsoAdapter.BitsoViewHolder>(DiffCallback()) {

    private class DiffCallback : DiffUtil.ItemCallback<Bitso>() {
        override fun areItemsTheSame(oldItem: Bitso, newItem: Bitso): Boolean {
            return oldItem.book == newItem.book
        }

        override fun areContentsTheSame(oldItem: Bitso, newItem: Bitso): Boolean {
            return oldItem == newItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BitsoViewHolder {
        val binding = ListItemBitsoBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return BitsoViewHolder(binding)
    }

    override fun onBindViewHolder(holder: BitsoViewHolder, position: Int) = holder.bind()

    inner class BitsoViewHolder(private val binding: ListItemBitsoBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind() {
            val currentItem = getItem(adapterPosition)
            with(binding) {
                tvMaximumAmount.text = currentItem.maximumAmount
                tvMaximumPrice.text = currentItem.maximumPrice
                tvNameCurrency.text = currentItem.name
                ivIconCurrency.loadUrl(currentItem.imgUrl)
            }
        }
    }
}