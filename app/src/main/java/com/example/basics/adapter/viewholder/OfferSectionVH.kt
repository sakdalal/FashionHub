package com.example.basics.adapter.viewholder

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.basics.adapter.OfferAdapter
import com.example.basics.databinding.ItemOfferBinding
import com.example.basics.databinding.ItemSectionBinding
import com.example.basics.listener.OnProductClickListener
import com.example.basics.model.OfferSmall
import com.example.basics.model.Product

class OfferSectionVH(private val binding: ItemOfferBinding,private val listener: OnProductClickListener,sharedPool: RecyclerView.RecycledViewPool) :
    RecyclerView.ViewHolder(binding.root) {

    private val offerAdapter = OfferAdapter(listener)

    init{
        binding.recyclerView.layoutManager =
            LinearLayoutManager(binding.root.context, RecyclerView.HORIZONTAL, false)
        binding.recyclerView.adapter=offerAdapter
        binding.recyclerView.setRecycledViewPool(sharedPool)
        binding.recyclerView.setItemViewCacheSize(10)
        binding.recyclerView.isNestedScrollingEnabled = false
    }

    fun bind(offers: List<Product>) {
            offerAdapter.submitList(offers)
    }
}