package com.example.basics.adapter

import android.R
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.basics.model.OfferSmall
import com.example.basics.databinding.OfferSmallBinding
import com.example.basics.listener.OnProductClickListener
import com.example.basics.model.Product

class OfferAdapter(private val listener: OnProductClickListener): ListAdapter<Product, OfferAdapter.ViewHolder>(ProductDiffCallBack()){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = OfferSmallBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            holder.bind(getItem(position),listener)
    }

    class ViewHolder(val binding: OfferSmallBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(offer: Product,listener: OnProductClickListener){

            Glide.with(binding.root.context)
                .load(offer.image)
                .override(300,510)
                .placeholder(R.color.darker_gray)
                .into(binding.offerImg)

            binding.root.setOnClickListener {
                listener.onOtherProductClick(offer)
            }


        }
    }
}