package com.example.basics.adapter.viewholder

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.basics.adapter.SellerAdapter
import com.example.basics.databinding.ItemBestsellerBinding
import com.example.basics.databinding.ItemSectionBinding
import com.example.basics.listener.OnProductClickListener
import com.example.basics.model.Product
import com.example.basics.model.Seller

class SellerSectionVH(private val binding: ItemBestsellerBinding, private val listener: OnProductClickListener, sharedPool: RecyclerView.RecycledViewPool) :
    RecyclerView.ViewHolder(binding.root) {
        private val sellerAdapter= SellerAdapter(listener)

        init {
            binding.recyclerView.layoutManager =
                LinearLayoutManager(binding.root.context, RecyclerView.HORIZONTAL, false)
            binding.recyclerView.adapter=sellerAdapter
            binding.recyclerView.setRecycledViewPool(sharedPool)
            binding.recyclerView.setItemViewCacheSize(10)
            binding.recyclerView.isNestedScrollingEnabled = false
        }

    fun bind(seller: List<Product>) {
        sellerAdapter.submitList(seller)
    }
}