package com.example.basics.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.basics.db.CartEntity
import com.example.basics.db.OrderedEntity
import com.example.basics.repository.CartRepository
import com.example.basics.repository.OrderRepository
import kotlinx.coroutines.launch

class CartViewModel(private val repository: CartRepository,private val orderRepository: OrderRepository): ViewModel() {

    val cartItems = repository.getCartItems()

    fun addToCart(item: CartEntity) {
        viewModelScope.launch {
            repository.addToCart(item)
        }
    }

    fun removeFromCart(id: Int) {
        viewModelScope.launch {
            repository.removeFromCart(id)
        }
    }

    suspend fun isAddedToCart(id: Int): Boolean {
        return repository.isAddedToCart(id)
    }


    fun placeOrder() {

        viewModelScope.launch {

            val cartItems = repository.getCartItemsList()

            if (cartItems.isEmpty()) return@launch

            val orderId = "ORD-${System.currentTimeMillis()}"

            val orderedItems = cartItems.map {

                OrderedEntity(
                    id = it.id,
                    rating = it.rating,
                    brand = it.brand,
                    title = it.title,
                    price = it.price,
                    discountPercentage = it.discountPercentage,
                    returnPolicy = it.returnPolicy,
                    thumbnail = it.thumbnail,
                    orderId = orderId
                )
            }

            orderRepository.addAllToOrder(orderedItems)

            repository.clearCart()
        }
    }

}