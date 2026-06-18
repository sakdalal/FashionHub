package com.example.basics.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.basics.repository.AddressRepository

class AddressViewModelFactory(
    private val repository: AddressRepository
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {

        if (modelClass.isAssignableFrom(AddressViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return AddressViewModel(repository) as T
        }

        throw IllegalArgumentException("Unknown ViewModel class")
    }
}