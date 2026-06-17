package com.example.basics.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow


@Dao
interface OrderDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(item: OrderedEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(items: List<OrderedEntity>)

    @Query("DELETE FROM `ordered` WHERE id = :id")
    suspend fun delete(id: Int)

    @Query("SELECT * FROM `ordered` ORDER BY orderId DESC")
    fun getAllOrderedItems(): Flow<List<OrderedEntity>>

    @Query("""SELECT * FROM `ordered` WHERE orderId = :orderId AND id = :productId LIMIT 1 """)
    suspend fun getOrderedItem(
        orderId: String,
        productId: Int
    ): OrderedEntity?

    @Query("SELECT * FROM ordered WHERE orderId = :orderId")
    suspend fun getItemsByOrderId(orderId: String): List<OrderedEntity>

}