package com.example.basics.repository

import com.example.basics.model.Banner
import com.example.basics.model.RailItem
import com.example.basics.network.RetrofitInstance

class RailRepository {
    suspend fun getRails(): List<RailItem>{
        val response = RetrofitInstance.dummyApi.getDummyProducts()
        return response.products.take(5).map{
            RailItem(
                id=it.id,
                category = it.category,
                thumbnail = it.thumbnail
            )
        }
    }

}