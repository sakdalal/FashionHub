package com.example.basics.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "address")
data class AddressEntity (
    @PrimaryKey(autoGenerate = true)
    val id:Int,

    val userId: String,
    val name: String,
    val mobile: String,
    val address: String,
    val city: String,
    val state: String,
    val pincode: String,
    val house: String,
    val addressType: String,
    val defaultAddress: Boolean

)