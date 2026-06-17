package com.example.basics.db

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [WishlistEntity::class,
               CartEntity::class,
               OrderedEntity::class],
    version = 6
)
abstract class AppDatabase: RoomDatabase() {
    abstract fun wishlistDao():WishlistDao
    abstract fun cartDao(): CartDao

    abstract fun orderDao(): OrderDao
}