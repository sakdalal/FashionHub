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
import com.example.basics.model.Banner
import com.example.basics.databinding.TopBannerBinding

class BannerAdapter: ListAdapter<Banner, BannerAdapter.BannerPageVH>(BannerDiffCallBack()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BannerPageVH {
        val binding = ItemBannerPageBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return BannerPageVH(binding)
    }

    override fun onBindViewHolder(holder: BannerPageVH, position: Int) {
         holder.bind(getItem(position))
    }
    class BannerPageVH(val binding: ItemBannerPageBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(banner: Banner){
            binding.bigText.text=banner.title
            binding.smallText.text=banner.discountPercentage
            Glide.with(binding.root.context)
                .load(banner.thumbnail)
                .placeholder(R.color.holo_blue_light)
                .into(binding.bannerImage)
        }
    }
}