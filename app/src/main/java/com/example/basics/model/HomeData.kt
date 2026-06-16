package com.example.basics.model

data class HomeData (
    val banners: List<Banner>,

    val cards: List<Card>,

    val offers: List<OfferSmall>,

    val featureBrands: List<FeatureBrand>,

    val bestSellers: List<Seller>,

    val products: List<Product>
)