package com.example.basics.adapter

import androidx.recyclerview.widget.DiffUtil
import com.example.basics.model.RailItem

class RailDiffCallBack: DiffUtil.ItemCallback<RailItem>() {
    override fun areItemsTheSame(
        oldItem: RailItem,
        newItem: RailItem
    ): Boolean {
            return oldItem.id==newItem.id
    }

    override fun areContentsTheSame(
        oldItem: RailItem,
        newItem: RailItem
    ): Boolean {
        return oldItem==newItem
    }
}