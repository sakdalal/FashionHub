package com.example.basics.adapter

import androidx.recyclerview.widget.DiffUtil
import com.example.basics.model.FeatureBrand

class FeatureDiffCallBack: DiffUtil.ItemCallback<FeatureBrand>() {
    override fun areItemsTheSame(
        oldItem: FeatureBrand,
        newItem: FeatureBrand
    ): Boolean {
        return oldItem.id==newItem.id
    }

    override fun areContentsTheSame(
        oldItem: FeatureBrand,
        newItem: FeatureBrand
    ): Boolean {
        return oldItem==newItem
    }
}