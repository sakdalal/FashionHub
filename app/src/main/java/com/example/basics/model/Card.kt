package com.example.basics.model

import com.google.gson.annotations.SerializedName

data class Card(
    @SerializedName("id")
    val id:Int,
    @SerializedName("category")
    val category: String,
    @SerializedName("thumbnail")
    val thumbnail: String
)

//data class Card (
//    @SerializedName("_id")
//    val id:Int ,
//    @SerializedName("brand")
//    var brand: String,
//    @SerializedName("image")
//    var image:String)