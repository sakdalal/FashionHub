package com.example.basics.adapter.viewholder

import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.basics.adapter.CardAdapter
import com.example.basics.databinding.ItemSectionBinding
import com.example.basics.listener.OnProductClickListener
import com.example.basics.model.Card
import com.example.basics.model.FeatureBrand

class CardSectionVH(private val binding: ItemSectionBinding,listener: OnProductClickListener) :
    RecyclerView.ViewHolder(binding.root) {

       private val cardAdapter=CardAdapter(listener)

        init {
            binding.recyclerView.layoutManager =
                GridLayoutManager(binding.root.context,2, RecyclerView.HORIZONTAL, false)
            binding.recyclerView.adapter=cardAdapter
        }

    fun bind(cards: List<FeatureBrand>) {
        cardAdapter.submitList(cards)
    }
}