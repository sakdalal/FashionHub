package com.example.basics.model

import com.google.gson.annotations.SerializedName

data class OfferSmall (
    @SerializedName("id")
    val id: String,
    @SerializedName("image")
    val image: String
)

//data class OfferSmall(
//    @SerializedName("_id")
//    val _id:Int,
//    @SerializedName("image")
//    val image:String)