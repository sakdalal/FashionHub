package com.example.basics.db

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.security.Policy

@Entity(tableName = "cart")
data class CartEntity (
    @PrimaryKey
    val id:Int,

    val rating:String,
    val brand:String?,
    val title:String,
    val price:String,
    val discountPercentage:String,
    val returnPolicy: String,
    val thumbnail:String
)

