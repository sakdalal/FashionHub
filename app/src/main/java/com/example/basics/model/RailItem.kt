package com.example.basics.model

import com.google.gson.annotations.SerializedName


data class RailItem (
    @SerializedName("id")
    val id:Int,
    @SerializedName("category")
    val category: String,
    @SerializedName("thumbnail")
    val thumbnail: String
)

//data class RailItem (
//    var title:String,
//    var image:String
//)