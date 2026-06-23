package com.example.basics.network


import com.example.basics.model.FeatureBrand
import com.example.basics.model.FeatureResponse
import com.example.basics.model.Product

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



}