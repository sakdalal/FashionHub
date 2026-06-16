package com.example.basics.adapter

import android.R
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.basics.model.RailItem
import com.example.basics.databinding.TopRailBinding
import javax.microedition.khronos.opengles.GL

class RailAdapter: ListAdapter<RailItem, RailAdapter.MyViewHolder>(RailDiffCallBack()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding= TopRailBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder:MyViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class MyViewHolder(val binding: TopRailBinding): RecyclerView.ViewHolder(binding.root){

        fun bind(rail: RailItem){
            binding.itemName.text=rail.category
            Glide.with(binding.itemImg.context)
                .load(rail.thumbnail)
                .placeholder(R.color.darker_gray)
                .into(binding.itemImg)

        }
    }

}