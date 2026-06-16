package com.example.basics.network

import com.example.basics.model.BannerResponse
import com.example.basics.model.CardResponse
import com.example.basics.model.FeatureBrand
import com.example.basics.model.FeatureResponse
import com.example.basics.model.OfferResponse
import com.example.basics.model.Product
import com.example.basics.model.RailResponse
import com.example.basics.model.Seller
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {

    @GET("products")
    suspend fun getDummyProducts(): FeatureResponse

    @GET("products")
    suspend fun getJsonFakeryProducts(): List<Product>

    @GET("products/{id}")
    suspend fun getProductById(
        @Path("id") id: Int
    ): FeatureBrand

//    @GET("api/products")
//    suspend fun getRails(): RailResponse
//    @GET("products")
//    suspend fun getBanners(): BannerResponse
//    @GET("api/walmartproducts")
//    suspend fun getCards(): CardResponse
//    @GET("api/products")
//    suspend fun getOffers(): OfferResponse
//    @GET("products")
//    suspend fun getFeatureBrands(): FeatureResponse
//    @GET("products")
//    suspend fun getBestSeller(): List<Seller>
//    @GET("products")
//    suspend fun getProducts(): List<Product>
//


}