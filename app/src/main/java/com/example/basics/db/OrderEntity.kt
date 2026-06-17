package com.example.basics.db


import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "ordered")
data class OrderedEntity (

    @PrimaryKey
    val id:Int,

    val rating:String,
    val brand:String?,
    val title:String,
    val price:String,
    val discountPercentage:String,
    val returnPolicy: String,
    val orderId: String,
    val thumbnail:String

)