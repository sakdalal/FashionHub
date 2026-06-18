package com.example.basics.repository

import com.example.basics.db.CartEntity
import com.example.basics.db.OrderDao
import com.example.basics.db.OrderedEntity
import kotlinx.coroutines.flow.Flow

class OrderRepository(private val dao: OrderDao) {
    suspend fun addToOrder(item: OrderedEntity) {
        dao.insert(item)
    }

    suspend fun removeFromOrder(id: Int) {
        dao.delete(id)
    }

    fun getOrderItems() =
        dao.getAllOrderedItems()


    suspend fun addAllToOrder(items: List<OrderedEntity>) {
        dao.insertAll(items)
    }

    suspend fun getOrderedItem(
        orderId: String,
        productId: Int
    ): OrderedEntity? {
        return dao.getOrderedItem(orderId, productId)
    }

    suspend fun getItemsByOrderId(orderId: String): List<OrderedEntity> {
        return dao.getItemsByOrderId(orderId)
    }

    fun getOrdersByUserId(userId: String): Flow<List<OrderedEntity>> {
        return dao.getOrdersByUserId(userId)
    }
}
