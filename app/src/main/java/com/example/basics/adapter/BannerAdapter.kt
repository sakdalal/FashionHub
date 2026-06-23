package com.example.basics.adapter

import android.R
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.basics.databinding.ItemBannerBinding
import com.example.basics.databinding.ItemBannerPageBinding
import com.example.basics.databinding.TopBannerBinding
import com.example.basics.listener.OnProductClickListener
import com.example.basics.model.FeatureBrand

class BannerAdapter(private val listener: OnProductClickListener): ListAdapter<FeatureBrand, BannerAdapter.BannerPageVH>(FeatureDiffCallBack()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BannerPageVH {
        val binding = ItemBannerPageBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return BannerPageVH(binding,listener)
    }

    override fun onBindViewHolder(holder: BannerPageVH, position: Int) {
         holder.bind(getItem(position))
    }
    class BannerPageVH(val binding: ItemBannerPageBinding,private val listener: OnProductClickListener): RecyclerView.ViewHolder(binding.root){
        fun bind(banner: FeatureBrand){
            binding.bigText.text=banner.title
            binding.smallText.text=banner.discountPercentage
            Glide.with(binding.root.context)
                .load(banner.thumbnail)
                .placeholder(R.color.holo_blue_light)
                .into(binding.bannerImage)

            binding.root.setOnClickListener {
                listener.onProductClick(banner)
            }

        }
    }
}