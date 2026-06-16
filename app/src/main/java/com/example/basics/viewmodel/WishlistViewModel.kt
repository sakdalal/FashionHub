package com.example.basics.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.basics.db.WishlistEntity
import com.example.basics.repository.WishlistRepository
import kotlinx.coroutines.launch

class WishlistViewModel(
    private val repository: WishlistRepository
) : ViewModel() {

    val wishlistItems =
        repository.getWishlistItems()

    fun toggleWishlist(
        item: WishlistEntity
    ) {
        viewModelScope.launch {
            val exists =
                repository.isWishlisted(item.id)
            if (exists) {
                repository.removeFromWishlist(
                    item.id
                )
            } else {
                repository.addToWishlist(
                    item
                )
            }
        }
    }

    fun addToWishlist(
        item: WishlistEntity
    ) {
        viewModelScope.launch {
            repository.addToWishlist(item)
        }
    }
    fun removeFromWishlist(
        productId: Int
    ) {
        viewModelScope.launch {
            repository.removeFromWishlist(productId)
        }
    }

    suspend fun isWishlisted(id: Int): Boolean {
        return repository.isWishlisted(id)
    }
}