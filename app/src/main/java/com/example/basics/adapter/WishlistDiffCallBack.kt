package com.example.basics.adapter

import androidx.recyclerview.widget.DiffUtil
import com.example.basics.db.WishlistEntity

class WishlistDiffCallBack: DiffUtil.ItemCallback<WishlistEntity>() {
    override fun areItemsTheSame(
        oldItem: WishlistEntity,
        newItem: WishlistEntity
    ): Boolean {
        return oldItem.id==newItem.id
    }

    override fun areContentsTheSame(
        oldItem: WishlistEntity,
        newItem: WishlistEntity
    ): Boolean {
        return oldItem==newItem
    }
}