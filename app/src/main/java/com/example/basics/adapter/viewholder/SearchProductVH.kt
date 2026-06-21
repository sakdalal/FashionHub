package com.example.basics.adapter.viewholder

import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.basics.adapter.ProductAdapter
import com.example.basics.databinding.ItemProductSectionBinding
import com.example.basics.model.Product

class SearchProductVH(
    private val binding: ItemProductSectionBinding
) : RecyclerView.ViewHolder(binding.root) {

    private val adapter = ProductAdapter()

    init {
        binding.productRecycler.layoutManager =
            GridLayoutManager(binding.root.context, 2)

        binding.productRecycler.adapter = adapter

        binding.tabsLayout.visibility = View.GONE
    }

    fun bind(products: List<Product>) {
        adapter.submitList(products)
    }
}