package com.example.basics.adapter

import android.R
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.basics.databinding.TopRailBinding
import com.example.basics.listener.OnProductClickListener
import com.example.basics.model.FeatureBrand
import javax.microedition.khronos.opengles.GL

class RailAdapter(private val listener: OnProductClickListener): ListAdapter<FeatureBrand, RailAdapter.MyViewHolder>(FeatureDiffCallBack()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding= TopRailBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return MyViewHolder(binding,listener)
    }

    override fun onBindViewHolder(holder:MyViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class MyViewHolder(val binding: TopRailBinding,private val listener: OnProductClickListener): RecyclerView.ViewHolder(binding.root){

        fun bind(rail: FeatureBrand){
            binding.itemName.text=rail.category
            Glide.with(binding.itemImg.context)
                .load(rail.thumbnail)
                .placeholder(R.color.darker_gray)
                .into(binding.itemImg)

            binding.root.setOnClickListener {
                listener.onProductClick(rail)
            }

        }
    }

}