package com.example.basics.adapter.viewholder

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.basics.adapter.SellerAdapter
import com.example.basics.databinding.ItemSectionBinding
import com.example.basics.model.Seller

class SellerSectionVH(private val binding: ItemSectionBinding) :
    RecyclerView.ViewHolder(binding.root) {

        private val sellerAdapter= SellerAdapter()

        init {
            binding.recyclerView.layoutManager =
                LinearLayoutManager(binding.root.context, RecyclerView.HORIZONTAL, false)
            binding.recyclerView.adapter=sellerAdapter
        }

    fun bind(seller: List<Seller>) {
        sellerAdapter.submitList(seller)
    }
}