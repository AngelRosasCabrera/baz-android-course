package com.example.bitsocurrency.ui.adapters

import android.content.Context
import android.content.res.ColorStateList
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.widget.TextViewCompat
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.bitsocurrency.R
import com.example.bitsocurrency.databinding.ListItemBitsoBinding
import com.example.bitsocurrency.domain.models.Bitso
import com.example.bitsocurrency.utils.diff.DiffCallback
import com.example.bitsocurrency.utils.extensions.formatAsCurrency
import com.example.bitsocurrency.utils.extensions.formatAsPercent
import com.example.bitsocurrency.utils.extensions.loadUrl

class BitsoAdapter(
    private val onItemClickListener: BitsoOnItemClickListener,
    private val context: Context
) : ListAdapter<Bitso, BitsoAdapter.BitsoViewHolder>(DiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BitsoViewHolder {
        val binding = ListItemBitsoBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return BitsoViewHolder(binding)
    }

    override fun onBindViewHolder(holder: BitsoViewHolder, position: Int) = holder.bind()

    inner class BitsoViewHolder(private val binding: ListItemBitsoBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind() {
            val currentItem = getItem(adapterPosition)
            val color = if(currentItem.tickSize.toDouble() < 0.5) R.color.color_danger else R.color.color_success
            val drawable = if(currentItem.tickSize.toDouble() < 0.5) R.drawable.ic_arrow_down else R.drawable.ic_arrow_up
            val colorList = ColorStateList.valueOf(ContextCompat.getColor(context, color))
            with(binding) {
                tvCurrencyName.text = currentItem.name
                tvCurrencySymbol.text = currentItem.symbol
                tvMaximumPrice.text = currentItem.maximumPrice.toDouble().formatAsCurrency()
                tvTickSize.text = currentItem.tickSize.toDouble().formatAsPercent()
                tvTickSize.setTextColor(colorList)
                tvTickSize.setCompoundDrawablesWithIntrinsicBounds(null,null,ContextCompat.getDrawable(context, drawable),null)
                TextViewCompat.setCompoundDrawableTintList(tvTickSize, colorList)
                ivIconCurrency.loadUrl(currentItem.imgUrl)
                root.setOnClickListener{ onItemClickListener.onItemClickListener(currentItem) }
            }
        }
    }

    interface BitsoOnItemClickListener {
        fun onItemClickListener(bitso: Bitso)
    }
}