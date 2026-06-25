package com.example.basics.adapter

import androidx.recyclerview.widget.DiffUtil
import com.example.basics.model.HomeItem

class HomeDiffCallBack: DiffUtil.ItemCallback<HomeItem>() {
    override fun areItemsTheSame(
        oldItem: HomeItem,
        newItem: HomeItem
    ): Boolean {
        return oldItem::class == newItem::class
    }

    override fun areContentsTheSame(
        oldItem : HomeItem,
        newItem : HomeItem
    ): Boolean {
        return oldItem == newItem

    }
}