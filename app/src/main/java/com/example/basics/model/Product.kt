package com.example.basics.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Product(
    @SerializedName("id")
    val id:String,
    @SerializedName("image")
    val image: String,
//    val rate: String,
//    val serial: String,
    @SerializedName("manufacturer")
    val manufacturer: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("price")
    val price: String,
//    val coupon: String
) : Serializable