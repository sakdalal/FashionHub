package com.example.basics.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.CreationExtras
import com.example.basics.repository.OrderRepository

class OrderViewModelFactory(private val repository: OrderRepository): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>, extras: CreationExtras): T {
        return OrderViewModel(repository) as T
    }
}