package com.example.basics.adapter

import androidx.recyclerview.widget.DiffUtil
import com.example.basics.db.OrderedEntity

class OrderDiffCallBack: DiffUtil.ItemCallback<OrderedEntity>() {
    override fun areItemsTheSame(
        oldItem: OrderedEntity,
        newItem: OrderedEntity
    ): Boolean {
        return oldItem.id==newItem.id
    }

    override fun areContentsTheSame(
        oldItem: OrderedEntity,
        newItem: OrderedEntity
    ): Boolean {
        return oldItem==newItem
    }
}