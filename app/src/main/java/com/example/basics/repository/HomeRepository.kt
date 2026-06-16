package com.example.basics.repository

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
                Banner(
                id=it.id,
                thumbnail =it.thumbnail,
                title=it.title,
                discountPercentage="UpTo ${it.discountPercentage}% Off"
                )
            },
            cards = dummyResponse.products.drop(5).take(12).map {
                Card(
                    id = it.id,
                    category = it.category,
                    thumbnail = it.thumbnail
                )
            },
            offers = fakeryResponse.take(6).map {
                OfferSmall(
                    id=it.id,
                    image = it.image
                )
            },
            featureBrands = dummyResponse.products.take(5).map {
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
//                smallOne = it.image,
//                smallTwo = it.image
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

//    suspend fun getBannerData(): List<Banner>{
//        val response =RetrofitInstance.bannerApi.getBanners()
//        return response.products.take(5).map {
//            Banner(
//                id=it.id,
//                thumbnail =it.thumbnail,
//                title=it.title,
//                discountPercentage="UpTo ${it.discountPercentage}% Off"
//            )
//        }
//    }
//    suspend fun getCardData(): List<Card>{
//        val response = RetrofitInstance.cardApi.getCards()
//        return response.data
//    }
//    suspend fun getOfferData(): List<OfferSmall>{
//        val response = RetrofitInstance.offerApi.getOffers()
//        return response.data.take(7)
////            OfferSmall(
////                _id = it._id,
////                image = it.image
////            )
////        }
//    }
//    suspend fun getFeatureData(): List<FeatureBrand>{
//        val response= RetrofitInstance.featureApi.getFeatureBrands()
//        return response.products.take(10).map{
//            FeatureBrand(
//                id = it.id,
//                title = it.title,
//                discountPercentage = "UpTo ${it.discountPercentage}% Off",
//                thumbnail = it.thumbnail,
//                image = it.image
//            )
//        }
//    }
//
//    suspend fun getSellerData(): List<Seller>{
//        val response= RetrofitInstance.sellerApi.getBestSeller()
//        return response.take(10).map {
//            Seller(
//                id = it.id,
//                name = it.name,
//                image = it.image,
//                price = "Under $${it.price}"
////                smallOne = it.image,
////                smallTwo = it.image
//            )
//        }
//    }
//
//    suspend fun getProductData(): List<Product>{
//        val response= RetrofitInstance.productApi.getProducts()
//        return response.drop(10).map {
//            Product(
//                id = it.id,
//                image = it.image,
//                manufacturer = it.manufacturer,
//                name = it.name,
//                price = "Best Price $${it.price}"
//            )
//        }
//    }

}