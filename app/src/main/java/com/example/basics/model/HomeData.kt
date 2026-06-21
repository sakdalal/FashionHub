package com.example.basics.model

data class HomeData (
    val banners: List<FeatureBrand>,

    val cards: List<FeatureBrand>,

    val offers: List<OfferSmall>,

    val featureBrands: List<FeatureBrand>,

    val bestSellers: List<Seller>,

    val products: List<Product>,

//    val searchProducts: List<Product>
)