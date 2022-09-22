package com.example.bitsocurrency.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.bitsocurrency.databinding.ListItemBitsoBinding
import com.example.bitsocurrency.domain.models.Bitso
import com.example.bitsocurrency.utils.diff.DiffCallback
import com.example.bitsocurrency.utils.extensions.loadUrl

class BitsoAdapter(private val onItemClickListener: BitsoOnItemClickListener) : ListAdapter<Bitso, BitsoAdapter.BitsoViewHolder>(DiffCallback) {

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
                root.setOnClickListener{ onItemClickListener.onItemClickListener(currentItem) }
            }
        }
    }

    interface BitsoOnItemClickListener {
        fun onItemClickListener(bitso: Bitso)
    }
}