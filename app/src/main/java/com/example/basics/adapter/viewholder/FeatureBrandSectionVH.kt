package com.example.basics.adapter.viewholder

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.basics.adapter.FeatureBrandAdapter
import com.example.basics.databinding.ItemSectionBinding
import com.example.basics.listener.OnProductClickListener
import com.example.basics.model.FeatureBrand

class FeatureBrandSectionVH(private val binding: ItemSectionBinding,listener: OnProductClickListener,
                            sharedPool: RecyclerView.RecycledViewPool) :
    RecyclerView.ViewHolder(binding.root) {

    private val featureAdapter= FeatureBrandAdapter(listener)

    init{
        binding.recyclerView.layoutManager =
            LinearLayoutManager(binding.root.context, RecyclerView.HORIZONTAL, false)
        binding.recyclerView.adapter=featureAdapter
        binding.recyclerView.setRecycledViewPool(sharedPool)
        binding.recyclerView.setItemViewCacheSize(10)
        binding.recyclerView.isNestedScrollingEnabled = false
    }

    fun bind(featureBrands: List<FeatureBrand>, wishlistedIds: Set<Int>) {

        featureAdapter.updateWishlist(wishlistedIds)
        featureAdapter.submitList(featureBrands)
    }
}