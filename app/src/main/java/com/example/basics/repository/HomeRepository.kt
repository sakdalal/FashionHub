package com.example.basics.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.basics.model.Banner
import com.example.basics.model.Card
import com.example.basics.model.FeatureBrand
import com.example.basics.model.HomeData
import com.example.basics.model.OfferSmall
import com.example.basics.model.Product
import com.example.basics.model.Seller
import com.example.basics.network.RetrofitInstance

class HomeRepository {

    suspend fun getHomeData(): HomeData{
        val dummyResponse= RetrofitInstance.dummyApi.getDummyProducts()
        val fakeryResponse= RetrofitInstance.fakeryApi.getJsonFakeryProducts()

        return HomeData(

            banners = dummyResponse.products.take(5).map{
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
            },
            cards = dummyResponse.products.drop(5).take(12).map {
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
            },
            offers = fakeryResponse.take(6).map {
                OfferSmall(
                    id=it.id,
                    image = it.image
                )
            },
            featureBrands = dummyResponse.products.drop(10).take(5).map {
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
            },
            bestSellers = fakeryResponse.drop(6).take(6).map{
                Seller(
                id = it.id,
                name = it.name,
                image = it.image,
                price = "Under $${it.price}"
            )
            },
            products = fakeryResponse.drop(12).map{
                Product(
                id = it.id,
                image = it.image,
                manufacturer = it.manufacturer,
                name = it.name,
                price = "Best Price $${it.price}"
            )
            }
        )
    }


    suspend fun getSearchProducts(): List<FeatureBrand> {

        val dummyResponse =
            RetrofitInstance.dummyApi.getDummyProducts()

        return dummyResponse.products.map {

            FeatureBrand(
                id = it.id,
                title = it.title,
                discountPercentage = it.discountPercentage,
                thumbnail = it.thumbnail,
                image = it.image,
                category = it.category,
                price = it.price,
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