package com.example.basics.adapter

import androidx.recyclerview.widget.DiffUtil
import com.example.basics.db.CartEntity

class CartDiffCallBack: DiffUtil.ItemCallback<CartEntity>() {
    override fun areItemsTheSame(
        oldItem: CartEntity,
        newItem: CartEntity
    ): Boolean {
        return oldItem.id==newItem.id
    }

    override fun areContentsTheSame(
        oldItem: CartEntity,
        newItem: CartEntity
    ): Boolean {
        return  oldItem==newItem
    }
}