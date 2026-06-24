package com.example.basics.adapter.viewholder

import android.os.Handler
import android.os.Looper
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.example.basics.adapter.BannerAdapter
import com.example.basics.databinding.ItemBannerBinding
import com.example.basics.listener.OnProductClickListener
import com.example.basics.model.FeatureBrand

class BannerVH(private val binding: ItemBannerBinding,listener: OnProductClickListener, sharedPool: RecyclerView.RecycledViewPool) :
    RecyclerView.ViewHolder(binding.root) {
        private val bannerAdapter= BannerAdapter(listener)
        private val handler= Handler(Looper.getMainLooper())

        private val autoScrollRunnable= object: Runnable{
            override fun run() {
                val itemCount=bannerAdapter.itemCount
                if (itemCount > 1) {
                    val nextItem =
                        (binding.bannerViewPager.currentItem + 1) % itemCount

                    binding.bannerViewPager.setCurrentItem(
                        nextItem,
                        true
                    )
                }
                handler.postDelayed(this, 5000)
            }
        }

        init {
            binding.bannerViewPager.adapter = bannerAdapter
            binding.dotsIndicator.attachTo(binding.bannerViewPager)
        }

    fun bind(banners: List<FeatureBrand>) {
        bannerAdapter.submitList(banners)
        handler.removeCallbacks(autoScrollRunnable)

        if (banners.size > 1) {
            handler.postDelayed(autoScrollRunnable, 5000)
        }
    }
}