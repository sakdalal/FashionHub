package com.example.basics.adapter

import androidx.recyclerview.widget.DiffUtil
import com.example.basics.model.Seller

class SellerDiffCallBack: DiffUtil.ItemCallback<Seller>() {
    override fun areItemsTheSame(
        oldItem: Seller,
        newItem: Seller
    ): Boolean {
        return oldItem.id==newItem.id
    }

    override fun areContentsTheSame(
        oldItem: Seller,
        newItem: Seller
    ): Boolean {
        return oldItem==newItem
    }
}