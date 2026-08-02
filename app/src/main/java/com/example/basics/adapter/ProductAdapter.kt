package com.example.basics.adapter


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.basics.model.Product
import com.example.basics.databinding.ProductBinding
import com.example.basics.listener.OnProductClickListener

class ProductAdapter(private val listener: OnProductClickListener): ListAdapter<Product, ProductAdapter.ViewHolder>(ProductDiffCallBack()) {

    private val wishlistedIds = mutableSetOf<Int>()

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ):ViewHolder {
        val binding = ProductBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item=getItem(position)
        holder.bind(item,listener,item.id.hashCode() in wishlistedIds)

    }
    class ViewHolder(val binding: ProductBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(product: Product,listener: OnProductClickListener,isWishlisted: Boolean){
            binding.productName.text=product.name
            binding.companyName.text=product.manufacturer
            binding.bestPrice.text=product.price
            Glide.with(binding.root.context)
                .load(product.image)
                .fitCenter()
                .into(binding.productImage)

            binding.root.setOnClickListener {
                listener.onOtherProductClick(product)
            }

            binding.heart.setImageResource(
                if (isWishlisted)
                    com.example.basics.R.drawable.filledheart
                else
                    com.example.basics.R.drawable.heart
            )

            binding.heart.setOnClickListener {
                listener.onOtherWishlistClick(product)
            }
        }

    }

    fun updateWishlist(ids: Set<Int>) {
        wishlistedIds.clear()
        wishlistedIds.addAll(ids)
        notifyDataSetChanged()
    }

}