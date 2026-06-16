package com.example.basics.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.CreationExtras
import com.example.basics.repository.WishlistRepository

class WishlistViewModelFactory(private val repository: WishlistRepository): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(
        modelClass: Class<T>,
        extras: CreationExtras
    ): T {
        return super.create(modelClass, extras)
    }

    override fun <T : ViewModel> create(
        modelClass: Class<T>
    ): T {

        if (modelClass.isAssignableFrom(
                WishlistViewModel::class.java
            )
        ) {
            @Suppress("UNCHECKED_CAST")
            return WishlistViewModel(
                repository
            ) as T
        }

        throw IllegalArgumentException(
            "Unknown ViewModel Class"
        )
    }

}