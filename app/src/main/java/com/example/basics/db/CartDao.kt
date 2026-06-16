package com.example.basics.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface CartDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(item: CartEntity)

    @Query("DELETE FROM cart WHERE id = :id")
    suspend fun delete(id: Int)

    @Query("SELECT * FROM cart")
    fun getAllCartItems(): Flow<List<CartEntity>>

    @Query("SELECT EXISTS(SELECT * FROM cart WHERE id = :id)")
    suspend fun isAddedToCart(id: Int): Boolean

}