package com.example.basics.db

import android.view.Display
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName="wishlist")
data class WishlistEntity (
    @PrimaryKey
    val id: Int,

    val brand: String?,
    val rating:String,
    val price:String,
    val discountPercentage:String,
    val thumbnail:String,
    val title:String,
    val returnPolicy:String
)