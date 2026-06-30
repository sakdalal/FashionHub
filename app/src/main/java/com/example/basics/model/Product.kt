package com.example.basics.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Product(
    @SerializedName("id")
    val id:String,
    @SerializedName("name")
    val name: String,
    @SerializedName("image")
    val image: String,
    @SerializedName("price")
    val price: String,
    @SerializedName("manufacturer")
    val manufacturer: String,
    @SerializedName("description")
    val description: String
):Serializable

