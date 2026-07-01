package com.example.basics.model

data class HomeData (
    val banners: List<FeatureBrand>,

    val cards: List<FeatureBrand>,

    val offers: List<Product>,

    val featureBrands: List<FeatureBrand>,

    val bestSellers: List<Product>,

    val products: List<Product>,

//    val searchProducts: List<Product>
)