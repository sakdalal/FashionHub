package com.example.basics.model

import com.google.gson.annotations.SerializedName

data class Seller(
    @SerializedName("id")
    val id:String,
    @SerializedName("name")
    val name: String,
    @SerializedName("image")
    val image: String,
    @SerializedName("price")
    val price: String,
//    @SerializedName("image")
//    val smallOne: String,
//    @SerializedName("image")
//    val smallTwo: String
)