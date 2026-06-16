package com.example.basics.adapter

import android.R
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.basics.model.Seller
import com.example.basics.databinding.BestsellerBinding

class SellerAdapter: ListAdapter<Seller, SellerAdapter.ViewHolder>(SellerDiffCallBack()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = BestsellerBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            holder.bind(getItem(position))

    }

    class ViewHolder(val binding: BestsellerBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(seller: Seller){
            binding.title.text=seller.name
            binding.sellText.text=seller.price
            Glide.with(binding.root.context)
                .load(seller.image)
                .placeholder(R.color.holo_blue_light)
                .into(binding.sellImage)
        }
    }

}