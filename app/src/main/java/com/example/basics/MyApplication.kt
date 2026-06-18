package com.example.basics

import android.app.Application
import androidx.room.Room
import com.example.basics.db.AppDatabase
import com.example.basics.repository.WishlistRepository
import com.example.basics.repository.CartRepository
import com.example.basics.repository.OrderRepository
import com.example.basics.repository.AddressRepository

class MyApplication: Application() {

    val database by lazy{
        Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java,
            "my_database"
        )
        .fallbackToDestructiveMigration()
        .build()
    }

    val wishlistRepository by lazy {
        WishlistRepository(database.wishlistDao())
    }

    val cartRepository by lazy {
        CartRepository(database.cartDao())
    }

    val orderRepository by lazy {
        OrderRepository(database.orderDao())
    }

    val addressRepository by lazy {
        AddressRepository(database.addressDao())
    }

}