package com.example.basics.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.basics.databinding.CartProductBinding
import com.example.basics.db.CartEntity
import com.example.basics.db.WishlistEntity

class CartAdapter(private val onMoreClick: (CartEntity) -> Unit): ListAdapter<CartEntity, CartAdapter.MyViewHolder>(CartDiffCallBack()) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartAdapter.MyViewHolder {
        val binding= CartProductBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return MyViewHolder(binding,onMoreClick)
    }

    override fun onBindViewHolder(holder: CartAdapter.MyViewHolder, position: Int) {
       holder.bind(getItem(position))
    }


    class MyViewHolder(val binding: CartProductBinding,
                       private val onMoreClick: (CartEntity) -> Unit):
        RecyclerView.ViewHolder(binding.root){

        fun bind(item: CartEntity){
            binding.companyName.text=item.brand
            binding.title.text=item.title
            binding.price.text=item.price
            binding.discountPercentage.text="Upto ${item.discountPercentage} Off"
            binding.returnPolicy.text=item.returnPolicy
            Glide.with(binding.root.context)
                .load(item.thumbnail)
                .into(binding.productImage)


            binding.crossIcon.setOnClickListener {
                onMoreClick(item)
            }
        }

    }
}