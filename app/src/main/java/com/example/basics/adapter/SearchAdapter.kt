package com.example.basics.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.R
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.basics.databinding.ProductBinding
import com.example.basics.model.FeatureBrand
import com.example.basics.model.Product

class SearchAdapter: ListAdapter<FeatureBrand, SearchAdapter.MyViewHolder>(FeatureDiffCallBack()) {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MyViewHolder {
        val binding = ProductBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(
        holder: MyViewHolder,
        position: Int
    ) {
        holder.bind(getItem(position))
    }


    class MyViewHolder(val binding: ProductBinding) : RecyclerView.ViewHolder(binding.root){

        fun bind(product: FeatureBrand){
            binding.rate.text=product.rating
            binding.productName.text=product.title
            binding.companyName.text=product.brand
            Glide.with(binding.root.context)
                .load(product.thumbnail)
                .placeholder(android.R.color.holo_blue_light)
                .into(binding.productImage)
        }

    }

}