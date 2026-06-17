package com.example.basics.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.basics.databinding.OrderPlacedBinding
import com.example.basics.db.OrderedEntity

class OrderAdapter(private val onMoveClick: (OrderedEntity) -> Unit): ListAdapter<OrderedEntity, OrderAdapter.MyViewHolder>(OrderDiffCallBack()) {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MyViewHolder {
            val binding = OrderPlacedBinding.inflate(LayoutInflater.from(parent.context),parent,false)
            return MyViewHolder(binding,onMoveClick)
    }

    override fun onBindViewHolder(
        holder: MyViewHolder,
        position: Int
    ) {
        holder.bind(
            getItem(position),
            position,
            currentList
        )
    }


    class MyViewHolder(val binding: OrderPlacedBinding,
        private val onMoveClick: (OrderedEntity) -> Unit): RecyclerView.ViewHolder(binding.root){

        fun bind(
            item: OrderedEntity,
            position: Int,
            list: List<OrderedEntity>
        ) {

            binding.companyName.text = item.brand
            binding.title.text = item.title
            binding.price.text = item.price
            binding.rightArrow.setOnClickListener {
                onMoveClick(item)
            }

            Glide.with(binding.root.context)
                .load(item.thumbnail)
                .into(binding.productImage)

            // Separator logic
            if (position == 0) {
                binding.divider.visibility = android.view.View.GONE
            } else {

                val previousItem = list[position - 1]

                if (item.orderId != previousItem.orderId) {
                    binding.divider.visibility = android.view.View.VISIBLE
                } else {
                    binding.divider.visibility = android.view.View.GONE
                }
            }
        }

    }

}