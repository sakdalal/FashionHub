package com.example.basics.db

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [WishlistEntity::class,
               CartEntity::class],
    version = 5
)
abstract class AppDatabase: RoomDatabase() {
    abstract fun wishlistDao():WishlistDao
    abstract fun cartDao(): CartDao
}