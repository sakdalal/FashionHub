package com.example.basics.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.basics.db.CartEntity
import com.example.basics.repository.CartRepository
import kotlinx.coroutines.launch

class CartViewModel(private val repository: CartRepository): ViewModel() {

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

}