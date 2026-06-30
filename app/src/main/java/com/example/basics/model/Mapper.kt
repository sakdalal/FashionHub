package com.example.basics.model

fun Product.toFeatureBrand(): FeatureBrand {
    return FeatureBrand(
        id = id.hashCode(),
        title = name,
        brand = manufacturer,
        thumbnail = image,
        price = price,
        description = description,
        // Fields not present in this API
        rating = "4.5",
        discountPercentage = "0%",
        shippingInformation = "Free Delivery",
        availabilityStatus = "In Stock",
        warrantyInformation = "No Warranty",
        returnPolicy = "7 Days Return",
        category = "Appliances",
        stock = 37
    )
}