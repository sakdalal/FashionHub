package com.example.basics.db


import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "ordered")
data class OrderedEntity (

    @PrimaryKey(autoGenerate = true)
    val id:Int=0,

    val productId:Int,
    val rating:String,
    val brand:String?,
    val title:String,
    val price:String,
    val discountPercentage:String,
    val returnPolicy: String,
    val orderId: String,
    val thumbnail:String,
    val userId: String,
    val date:String


)