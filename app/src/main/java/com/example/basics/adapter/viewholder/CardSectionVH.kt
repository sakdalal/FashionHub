package com.example.basics.adapter.viewholder

import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.basics.adapter.CardAdapter
import com.example.basics.databinding.ItemSectionBinding
import com.example.basics.model.Card

class CardSectionVH(private val binding: ItemSectionBinding) :
    RecyclerView.ViewHolder(binding.root) {

       private val cardAdapter=CardAdapter()

        init {
            binding.recyclerView.layoutManager =
                GridLayoutManager(binding.root.context,2, RecyclerView.HORIZONTAL, false)
            binding.recyclerView.adapter=cardAdapter
        }

    fun bind(cards: List<Card>) {
        cardAdapter.submitList(cards)
    }
}