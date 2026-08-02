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

class ProductSectionVH(private val binding: ItemProductSectionBinding,listener: OnProductClickListener ) :
    RecyclerView.ViewHolder(binding.root) {
    private val adapter = ProductAdapter(listener)
//    private var tabsInitialized = false

    init{
        binding.productRecycler.layoutManager =
            GridLayoutManager(binding.root.context, 2)
        binding.productRecycler.adapter = adapter
//        binding.productRecycler.setRecycledViewPool(sharedPool)
        binding.productRecycler.setHasFixedSize(true)
        binding.productRecycler.setItemViewCacheSize(20)
        binding.productRecycler.isNestedScrollingEnabled = false
    }
    fun bind(item: HomeItem.ProductSection,wishlistedIds: Set<Int>) {
        adapter.updateWishlist(wishlistedIds)
        adapter.submitList(item.data[item.selectedTab] ?: emptyList())
            item.tabs.forEach { imageRes ->
                val tab = binding.tabsLayout.newTab()
                val view = LayoutInflater.from(binding.root.context)
                    .inflate(R.layout.tab_item, binding.tabsLayout, false)

                val image = view.findViewById<ImageView>(R.id.tabItem)
                image.setImageResource(imageRes)

                tab.customView = view
                binding.tabsLayout.addTab(tab)
                binding.tabsLayout.getTabAt(item.selectedTab)?.select()
            }
            binding.tabsLayout.clearOnTabSelectedListeners()
            binding.tabsLayout.addOnTabSelectedListener(
                object : TabLayout.OnTabSelectedListener {

                    override fun onTabSelected(tab: TabLayout.Tab) {
                        tab.customView?.alpha = 1f
                        item.selectedTab = tab.position
                        adapter.updateWishlist(wishlistedIds)
                        adapter.submitList(item.data[item.selectedTab] ?: emptyList())
                    }

                    override fun onTabUnselected(tab: TabLayout.Tab) {
                        tab.customView?.alpha = 0.75f
                    }

                    override fun onTabReselected(tab: TabLayout.Tab) {}
                }
            )



    }
}