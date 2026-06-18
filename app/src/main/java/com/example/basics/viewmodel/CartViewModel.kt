package com.example.basics.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.basics.db.CartEntity
import com.example.basics.db.OrderedEntity
import com.example.basics.repository.CartRepository
import com.example.basics.repository.OrderRepository
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

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


            val userId = FirebaseAuth.getInstance().currentUser?.uid ?: ""

            val currentDate = SimpleDateFormat(
                "dd MMMM yyyy",
                Locale.getDefault()
            ).format(Date())

            val orderId = "ORD-${System.currentTimeMillis()}"

            val orderedItems = cartItems.map {

                OrderedEntity(
                    id = 0,
                    productId=it.id,
                    rating = it.rating,
                    brand = it.brand,
                    title = it.title,
                    price = it.price,
                    discountPercentage = it.discountPercentage,
                    returnPolicy = it.returnPolicy,
                    thumbnail = it.thumbnail,
                    orderId = orderId,
                    userId = userId,
                    date = currentDate
                )
            }

            orderRepository.addAllToOrder(orderedItems)

            repository.clearCart()
        }
    }

}