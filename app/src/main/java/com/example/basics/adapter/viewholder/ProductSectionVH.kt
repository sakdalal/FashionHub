package com.example.basics.adapter.viewholder

import android.view.LayoutInflater
import android.widget.ImageView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.basics.R
import com.example.basics.adapter.ProductAdapter
import com.example.basics.databinding.ItemProductSectionBinding
import com.example.basics.listener.OnProductClickListener
import com.example.basics.model.HomeItem
import com.google.android.material.tabs.TabLayout

class ProductSectionVH(private val binding: ItemProductSectionBinding, sharedPool: RecyclerView.RecycledViewPool) :
    RecyclerView.ViewHolder(binding.root) {
    private val adapter = ProductAdapter()
//    private var tabsInitialized = false

    init{
        binding.productRecycler.layoutManager =
            GridLayoutManager(binding.root.context, 2)
        binding.productRecycler.adapter = adapter
        binding.productRecycler.setRecycledViewPool(sharedPool)
        binding.productRecycler.setHasFixedSize(true)
        binding.productRecycler.setItemViewCacheSize(20)
        binding.productRecycler.isNestedScrollingEnabled = false
    }
    fun bind(item: HomeItem.ProductSection) {
        adapter.submitList(item.data[0] ?: emptyList())
        binding.tabsLayout.removeAllTabs()
            item.tabs.forEach { imageRes ->
                val tab = binding.tabsLayout.newTab()
                val view = LayoutInflater.from(binding.root.context)
                    .inflate(R.layout.tab_item, binding.tabsLayout, false)

                val image = view.findViewById<ImageView>(R.id.tabItem)
                image.setImageResource(imageRes)

                tab.customView = view
                binding.tabsLayout.addTab(tab)
            }
            binding.tabsLayout.clearOnTabSelectedListeners()
            binding.tabsLayout.addOnTabSelectedListener(
                object : TabLayout.OnTabSelectedListener {

                    override fun onTabSelected(tab: TabLayout.Tab) {
                        tab.customView?.alpha = 1f
                        adapter.submitList(item.data[tab.position] ?: emptyList())
                    }

                    override fun onTabUnselected(tab: TabLayout.Tab) {
                        tab.customView?.alpha = 0.75f
                    }

                    override fun onTabReselected(tab: TabLayout.Tab) {}
                }
            )


    }
}