package com.example.basics.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class FeatureBrand(
    @SerializedName("id")
    val id: Int,

    @SerializedName("thumbnail")
    val thumbnail: String,

    @SerializedName("discountPercentage")
    val discountPercentage: String,

    @SerializedName("title")
    val title: String,

    @SerializedName("images")
    val image: List<String>,

    @SerializedName("category")
    val category: String,

    @SerializedName("price")
    val price: String,

    @SerializedName("brand")
    val brand: String?,

    @SerializedName("availabilityStatus")
    val availabilityStatus: String,

    @SerializedName("shippingInformation")
    val shippingInformation: String,

    @SerializedName("description")
    val description: String,

    @SerializedName("stock")
    val stock: Int,

    @SerializedName("warrantyInformation")
    val warrantyInformation: String,

    @SerializedName("returnPolicy")
    val returnPolicy: String,

    @SerializedName("rating")
    val rating: String

) : Serializable

