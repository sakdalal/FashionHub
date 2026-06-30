package com.example.basics.repository

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
                price = "Best Price $${it.price}",
                description = it.description
            )
            }
        )
    }


    suspend fun getSearchProducts(): List<FeatureBrand> {

        val dummyResponse =
            RetrofitInstance.dummyApi.getDummyProducts()

        val originalProducts = dummyResponse.products.map {

            FeatureBrand(
                id = it.id,
                title = it.title,
                discountPercentage = it.discountPercentage,
                thumbnail = it.thumbnail,
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

        return expandedProducts(originalProducts)
    }

    private val categories = listOf(
        "Women's Fashion", "Bags", "Men's Fashion", "Footwear", "Accessories", "Jewellery", "Beauty",
        "Skincare", "Fragrances", "Electronics", "Mobiles", "Laptops", "Smart Watches", "Gaming",
        "Home Decor", "Furniture", "Kitchen", "Sports", "Fitness", "Books", "Toys", "Groceries",
        "Pet Supplies", "Stationery", "Travel"
    )

    private val brands = listOf(
        "Nike","Adidas","Puma","Reebok","Levis",
        "Zara","H&M","Gucci","Prada","Louis Vuitton",
        "Samsung","Apple","Sony","JBL","Boat",
        "Fossil","Casio","Titan","RayBan","Oakley",
        "Mamaearth","Lakme","Maybelline","Loreal","Nykaa",
        "Ikea","Home Centre","Pepperfry","Wildcraft","Skybags",
        "OnePlus","Asus","HP","Dell","Lenovo",
        "Noise","FireBolt","Amazfit","Realme","Nothing"
    )

    private val productNames = listOf(
        "Floral Dress","Maxi Dress","Party Gown","Kurti",
        "Crop Top","Oversized T-Shirt","Formal Shirt","Denim Jacket",
        "Slim Fit Jeans","Cargo Pants","Sneakers","Running Shoes",
        "Leather Boots","Sports Sandals","Handbag","Shoulder Bag",
        "Backpack","Wallet","Watch","Smart Watch",
        "Wireless Earbuds","Bluetooth Speaker","Gaming Mouse",
        "Mechanical Keyboard","Laptop","Smartphone","Tablet",
        "Lipstick","Foundation","Face Wash","Perfume",
        "Sunscreen","Moisturizer","Coffee Table","Office Chair",
        "Dining Table","Wall Clock","Table Lamp","Yoga Mat",
        "Dumbbells","Cricket Bat","Football","Novel",
        "Cookbook","Toy Car","Teddy Bear","Dog Food",
        "Cat Food","Rice Pack","Coffee Powder","Water Bottle"
    )

    private fun expandedProducts(
        products: List<FeatureBrand>
    ): List<FeatureBrand> {

        val result = mutableListOf<FeatureBrand>()

        repeat(4) { round ->

            products.forEachIndexed { index, product ->

                val titleIndex =
                    (index + round * products.size) % productNames.size

                val brandIndex =
                    (index + round * products.size) % brands.size

                val categoryIndex =
                    (index + round * products.size) % categories.size

                result.add(
                    product.copy(
                        id = product.id + (round * 10000),

                        title = productNames[titleIndex],

                        brand = brands[brandIndex],

                        category = categories[categoryIndex]
                    )
                )
            }
        }

        return result
    }


}