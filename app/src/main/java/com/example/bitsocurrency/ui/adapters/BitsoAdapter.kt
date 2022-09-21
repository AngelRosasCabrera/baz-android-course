package com.example.bitsocurrency.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.bitsocurrency.databinding.ListItemBitsoBinding
import com.example.bitsocurrency.domain.models.Bitso
import com.example.bitsocurrency.utils.extensions.diffUtil
import com.example.bitsocurrency.utils.extensions.loadUrl

class BitsoAdapter : RecyclerView.Adapter<BitsoAdapter.BitsoViewHolder>() {

    var items: List<Bitso> by diffUtil { old, new -> old.book == new.book }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BitsoViewHolder {
        val binding = ListItemBitsoBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return BitsoViewHolder(binding)
    }

    override fun onBindViewHolder(holder: BitsoViewHolder, position: Int) = holder.bind(items)

    override fun getItemCount(): Int = items.size

    inner class BitsoViewHolder(private val binding: ListItemBitsoBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(items: List<Bitso>) {
            val currentItem = items[adapterPosition]
            with(binding) {
                tvMaximumAmount.text = currentItem.maximumAmount
                tvMaximumPrice.text = currentItem.maximumPrice
                tvNameCurrency.text = currentItem.name
                ivIconCurrency.loadUrl(currentItem.imgUrl)
            }
        }
    }
}