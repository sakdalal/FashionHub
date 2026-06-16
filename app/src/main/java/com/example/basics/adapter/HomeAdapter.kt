package com.example.basics.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.basics.R
import com.example.basics.adapter.viewholder.BannerVH
import com.example.basics.adapter.viewholder.CardSectionVH
import com.example.basics.adapter.viewholder.FeatureBrandSectionVH
import com.example.basics.adapter.viewholder.LoadingVH
import com.example.basics.adapter.viewholder.OfferSectionVH
import com.example.basics.adapter.viewholder.ProductSectionVH
import com.example.basics.adapter.viewholder.SellerSectionVH
import com.example.basics.databinding.ItemBannerBinding
import com.example.basics.databinding.ItemProductSectionBinding
import com.example.basics.databinding.ItemSectionBinding
import com.example.basics.listener.OnProductClickListener
import com.example.basics.model.HomeItem
import java.util.Collections

class HomeAdapter(private var list: List<HomeItem>, private val listener: OnProductClickListener) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var wishlistedIds = emptySet<Int>()



    override fun getItemViewType(position: Int): Int {
        Log.d(
            "HOME_ADAPTER",
            "type position=$position item=${list[position]::class.java.simpleName}"
        )

        return when (list[position]) {
            is HomeItem.BannerSection -> 0
            is HomeItem.CardSection -> 1
            is HomeItem.FeatureBrandSection -> 2
            is HomeItem.OfferSection -> 3
            is HomeItem.SellerSection -> 4
            is HomeItem.ProductSection -> 5

//            is HomeItem.BannerLoading -> 100
//            is HomeItem.CardLoading -> 101
//            is HomeItem.FeatureBrandLoading -> 102
//            is HomeItem.OfferLoading -> 103
//            is HomeItem.SellerLoading -> 104
//            is HomeItem.ProductLoading -> 105
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int)
            : RecyclerView.ViewHolder {

        val inflater = LayoutInflater.from(parent.context)
        Log.d("HOME_ADAPTER", "create holder type=$viewType")
        return when (viewType) {

//            100-> LoadingVH(inflater.inflate(R.layout.loading_banner,parent,false))
//            101-> LoadingVH(inflater.inflate(R.layout.loading_card,parent,false))
//            102-> LoadingVH(inflater.inflate(R.layout.loading_feature_brand,parent,false))
//            103-> LoadingVH(inflater.inflate(R.layout.loading_offer,parent,false))
//            104-> LoadingVH(inflater.inflate(R.layout.loading_seller,parent,false))
//            105-> LoadingVH(inflater.inflate(R.layout.loading_product,parent,false))

            0 -> BannerVH(ItemBannerBinding.inflate(inflater, parent, false))
            1 -> CardSectionVH(ItemSectionBinding.inflate(inflater, parent, false))
            2 -> FeatureBrandSectionVH(
                ItemSectionBinding.inflate(inflater, parent, false),
                listener
            )

            3 -> OfferSectionVH(ItemSectionBinding.inflate(inflater, parent, false))
            4 -> SellerSectionVH(ItemSectionBinding.inflate(inflater, parent, false))
            5 -> ProductSectionVH(ItemProductSectionBinding.inflate(inflater, parent, false))
            else -> throw Exception()
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = list[position]
        Log.d(
            "HOME_ADAPTER",
            "bind position=$position holder=${holder::class.java.simpleName}"
        )
        when (holder) {

//            is LoadingVH ->{ }

            is BannerVH -> holder.bind((item as HomeItem.BannerSection).banners)

            is CardSectionVH -> holder.bind((item as HomeItem.CardSection).cards)

            is FeatureBrandSectionVH -> holder.bind((item as HomeItem.FeatureBrandSection).featureBrands,wishlistedIds)

            is OfferSectionVH -> holder.bind((item as HomeItem.OfferSection).offers)

            is SellerSectionVH -> holder.bind((item as HomeItem.SellerSection).bestSellers)

            is ProductSectionVH -> holder.bind(item as HomeItem.ProductSection)
        }
    }

    override fun getItemCount(): Int {

        Log.d("COUNT", list.size.toString())

        return list.size
    }

    fun updateWishlist(ids: Set<Int>) {
        wishlistedIds = ids
        notifyDataSetChanged()
    }
    fun updateList(newList: List<HomeItem>) {
        list = newList
        notifyDataSetChanged()
    }
}