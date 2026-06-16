package com.example.basics.adapter

import androidx.recyclerview.widget.DiffUtil
import com.example.basics.model.Banner

class BannerDiffCallBack: DiffUtil.ItemCallback<Banner>() {

    override fun areItemsTheSame(
        oldItem: Banner,
        newItem: Banner
    ): Boolean {

        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(
        oldItem: Banner,
        newItem: Banner
    ): Boolean {

        return oldItem == newItem
    }
}