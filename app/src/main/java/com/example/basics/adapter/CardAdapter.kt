package com.example.basics.adapter

import android.R
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.basics.model.Card
import com.example.basics.databinding.TopCardGridBinding
import com.example.basics.listener.OnProductClickListener
import com.example.basics.model.FeatureBrand

class CardAdapter(private val listener: OnProductClickListener): ListAdapter<FeatureBrand, CardAdapter.MyViewHolder>(FeatureDiffCallBack()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = TopCardGridBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return MyViewHolder(binding,listener)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
            holder.bind(getItem(position))
    }
    class MyViewHolder(val binding: TopCardGridBinding,private val listener: OnProductClickListener): RecyclerView.ViewHolder(binding.root){

        fun bind(card: FeatureBrand){
            binding.cardText.text=card.category
            Glide.with(binding.root.context)
                .load(card.thumbnail)
                .placeholder(R.color.holo_green_light)
                .into(binding.cardImg)


            binding.root.setOnClickListener {
                listener.onProductClick(card)
            }

        }


    }


}