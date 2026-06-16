package com.example.basics.repository

import com.example.basics.db.CartDao
import com.example.basics.db.CartEntity

class CartRepository(private val dao: CartDao) {

    suspend fun addToCart(item: CartEntity) {
        dao.insert(item)
    }

    suspend fun removeFromCart(id: Int){
        dao.delete(id)
    }

    fun getCartItems()=
        dao.getAllCartItems()


    suspend fun isAddedToCart(id: Int): Boolean {
        return dao.isAddedToCart(id)
    }


}