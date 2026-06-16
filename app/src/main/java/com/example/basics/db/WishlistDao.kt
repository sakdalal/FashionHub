package com.example.basics.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface WishlistDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(item: WishlistEntity)

    @Query("DELETE FROM wishlist WHERE id = :id")
    suspend fun delete(id: Int)

    @Query("SELECT * FROM wishlist")
    fun getAllWishlistItems(): Flow<List<WishlistEntity>>

    @Query("SELECT EXISTS(SELECT * FROM wishlist WHERE id = :id)")
    suspend fun isWishlisted(id: Int): Boolean
}