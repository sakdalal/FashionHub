package com.example.basics.adapter

import android.R
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.basics.model.Card
import com.example.basics.databinding.TopCardGridBinding

class CardAdapter: ListAdapter<Card, CardAdapter.MyViewHolder>(CardDiffCallBack()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = TopCardGridBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
            holder.bind(getItem(position))
    }
    class MyViewHolder(val binding: TopCardGridBinding): RecyclerView.ViewHolder(binding.root){

        fun bind(card: Card){
            binding.cardText.text=card.category
            Glide.with(binding.root.context)
                .load(card.thumbnail)
                .placeholder(R.color.holo_green_light)
                .into(binding.cardImg)
        }
    }


}