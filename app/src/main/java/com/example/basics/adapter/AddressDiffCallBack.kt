package com.example.basics.adapter

import androidx.recyclerview.widget.DiffUtil
import com.example.basics.db.AddressEntity

class AddressDiffCallBack: DiffUtil.ItemCallback<AddressEntity>() {
    override fun areItemsTheSame(
        oldItem: AddressEntity,
        newItem: AddressEntity
    ): Boolean {
        return oldItem.id==newItem.id
    }

    override fun areContentsTheSame(
        oldItem: AddressEntity,
        newItem: AddressEntity
    ): Boolean {
        return oldItem==newItem
    }
}