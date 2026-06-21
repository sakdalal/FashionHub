package com.example.basics.adapter

import com.example.basics.R
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.basics.model.FeatureBrand
import com.example.basics.databinding.FeatureBrandBinding
import com.example.basics.listener.OnProductClickListener

class FeatureBrandAdapter(private val listener: OnProductClickListener): ListAdapter<FeatureBrand, FeatureBrandAdapter.MyViewHolder>(FeatureDiffCallBack()) {

    private val wishlistedIds = mutableSetOf<Int>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = FeatureBrandBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val item=getItem(position)
        holder.bind(item,listener,wishlistedIds.contains(item.id))

    }

    class MyViewHolder(val binding: FeatureBrandBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(featureBrand: FeatureBrand,listener: OnProductClickListener,isWishlisted: Boolean){
            binding.bigText.text=featureBrand.discountPercentage
            binding.smallText.text=featureBrand.title
            Glide.with(binding.root.context)
                .load(featureBrand.thumbnail)
                .into(binding.brandImg)
//            Glide.with(binding.root.context)
//                .load(featureBrand.image[0])
//                .into(binding.brandLogo)
            binding.root.setOnClickListener {
                listener.onProductClick(featureBrand)
            }

            binding.heart.setImageResource(
                if (isWishlisted)
                    com.example.basics.R.drawable.filledheart
                else
                    com.example.basics.R.drawable.heart
            )
            binding.heart.setOnClickListener {
                listener.onWishlistClick(featureBrand)
            }

        }


    }

    fun updateWishlist(ids: Set<Int>) {
        wishlistedIds.clear()
        wishlistedIds.addAll(ids)
        notifyDataSetChanged()
    }


}