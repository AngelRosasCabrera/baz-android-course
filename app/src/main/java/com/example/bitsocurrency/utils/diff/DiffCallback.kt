package com.example.bitsocurrency.utils.diff

import androidx.recyclerview.widget.DiffUtil
import com.example.bitsocurrency.domain.models.Bitso

object DiffCallback : DiffUtil.ItemCallback<Bitso>() {
    override fun areItemsTheSame(oldItem: Bitso, newItem: Bitso): Boolean {
        return oldItem.book == newItem.book
    }

    override fun areContentsTheSame(oldItem: Bitso, newItem: Bitso): Boolean {
        return oldItem == newItem
    }
}