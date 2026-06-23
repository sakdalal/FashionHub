package com.example.basics.repository

import com.example.basics.model.FeatureBrand
import com.example.basics.network.RetrofitInstance

class RailRepository {
    suspend fun getRails(): List<FeatureBrand>{
        val response = RetrofitInstance.dummyApi.getDummyProducts()
        return response.products.take(5).map{
            FeatureBrand(
                id = it.id,
                title = it.title,
                discountPercentage = "UpTo ${it.discountPercentage}% Off",
                thumbnail = it.thumbnail,
                image = it.image,
                category = it.category,
                price = "$ ${it.price}",
                availabilityStatus = it.availabilityStatus,
                brand = it.brand,
                shippingInformation = it.shippingInformation,
                description = it.description,
                stock = it.stock,
                warrantyInformation = it.warrantyInformation,
                returnPolicy = it.returnPolicy,
                rating = it.rating
            )
        }
    }

}