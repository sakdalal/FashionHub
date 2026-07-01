package com.example.basics.adapter

import androidx.recyclerview.widget.DiffUtil
import com.example.basics.model.OfferSmall
import com.example.basics.model.Product

class OfferDiffCallBack: DiffUtil.ItemCallback<OfferSmall>() {
    override fun areItemsTheSame(
        oldItem: OfferSmall,
        newItem: OfferSmall
    ): Boolean {
        return oldItem.id==newItem.id
    }

    override fun areContentsTheSame(
        oldItem: OfferSmall,
        newItem: OfferSmall
    ): Boolean {
        return oldItem==newItem
    }
}