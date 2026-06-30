package com.example.basics.listener

import com.example.basics.model.FeatureBrand
import com.example.basics.model.Product

interface OnProductClickListener {
    fun onProductClick(product: FeatureBrand)
    fun onWishlistClick(product: FeatureBrand)

    fun onOtherProductClick(product: Product)
}