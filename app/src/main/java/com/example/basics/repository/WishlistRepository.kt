package com.example.basics.repository

import com.example.basics.db.WishlistDao
import com.example.basics.db.WishlistEntity

class WishlistRepository(private val dao: WishlistDao)  {

    suspend fun addToWishlist(
        item: WishlistEntity
    ) {
        dao.insert(item)
    }

    suspend fun removeFromWishlist(
        productId: Int
    ) {
        dao.delete(productId)
    }

    fun getWishlistItems() =
        dao.getAllWishlistItems()

    suspend fun isWishlisted(
        id: Int
    ): Boolean {
        return dao.isWishlisted(id)
    }
}