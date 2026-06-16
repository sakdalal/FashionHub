package com.example.basics.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.CreationExtras
import com.example.basics.repository.CartRepository

class CartViewModelFactory(private val repository: CartRepository) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>, extras: CreationExtras): T {
        return CartViewModel(repository) as T
    }
}