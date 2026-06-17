package com.example.basics.viewmodel


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.basics.db.CartEntity
import com.example.basics.db.OrderedEntity
import com.example.basics.repository.OrderRepository
import kotlinx.coroutines.launch

class OrderViewModel(private val repository: OrderRepository): ViewModel() {
    val orderItems = repository.getOrderItems()
    fun addToCart(item: OrderedEntity) {
        viewModelScope.launch {
            repository.addToOrder(item)
        }
    }

    fun removeFromOrder(id: Int) {
        viewModelScope.launch {
            repository.removeFromOrder(id)
        }
    }

}