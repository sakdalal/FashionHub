package com.example.basics.model

sealed class HomeItem {


    data class BannerSection(val banners: List<Banner>) : HomeItem()

    data class CardSection(val cards: List<Card>) : HomeItem()

    data class FeatureBrandSection(val featureBrands: List<FeatureBrand>) : HomeItem()

    data class OfferSection(val offers: List<OfferSmall>) : HomeItem()

    data class SellerSection(val bestSellers: List<Seller>) : HomeItem()

    data class ProductSection( val tabs: List<Int>,
                               val data: Map<Int, List<Product>>) : HomeItem()
}