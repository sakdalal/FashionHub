package com.example.basics.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.basics.databinding.WishlistProductBinding
import com.example.basics.db.WishlistEntity
import com.example.basics.model.FeatureBrand

class WishlistAdapter(private val onDeleteClick: (WishlistEntity) -> Unit,private val onMoveClick:(WishlistEntity)->Unit): ListAdapter<WishlistEntity, WishlistAdapter.MyViewHolder>(WishlistDiffCallBack()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WishlistAdapter.MyViewHolder {
        val binding = WishlistProductBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return MyViewHolder(binding,onDeleteClick,onMoveClick)
    }

    override fun onBindViewHolder(holder: WishlistAdapter.MyViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class MyViewHolder(val binding: WishlistProductBinding,
                       private val onDeleteClick: (WishlistEntity) -> Unit,
                       private val onMoveClick: (WishlistEntity) -> Unit
    ):RecyclerView.ViewHolder(binding.root){

        fun bind(item: WishlistEntity){
            binding.rate.text=item.rating
            binding.companyName.text=item.brand
            binding.price.text=item.price
            Glide.with(binding.root.context)
                .load(item.thumbnail)
                .into(binding.productImage)

            binding.delete.setOnClickListener {
                onDeleteClick(item)
            }
            binding.moveToBagText.setOnClickListener {
                onMoveClick(item)
            }
        }
    }

}