package com.example.basics.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.basics.db.AddressEntity
import com.example.basics.repository.AddressRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class AddressViewModel(
    private val repository: AddressRepository
) : ViewModel() {

    fun insertAddress(address: AddressEntity) {
        viewModelScope.launch {
            try {
                repository.insertAddress(address)
                Log.d("ADDRESS_DEBUG", "Insert successful")
            }catch (e: Exception){
                Log.d("ADDRESS_DEBUG", "Insert failed", e)
            }
        }
    }

    fun insertAddresses(addresses: List<AddressEntity>) {
        viewModelScope.launch {
            repository.insertAddresses(addresses)
        }
    }

    fun updateAddress(address: AddressEntity) {
        viewModelScope.launch {
            repository.updateAddress(address)
        }
    }

    fun deleteAddress(address: AddressEntity) {
        viewModelScope.launch {
            repository.deleteAddress(address)
        }
    }

    fun deleteAddressById(id: Int) {
        viewModelScope.launch {
            repository.deleteAddressById(id)
        }
    }

    fun deleteAllAddresses() {
        viewModelScope.launch {
            repository.deleteAllAddresses()
        }
    }

    suspend fun getAllAddresses(): List<AddressEntity> {
        return repository.getAllAddresses()
    }

     fun getAddressesByUserId(userId: String): Flow<List<AddressEntity>> {
        return repository.getAddressesByUserId(userId)
    }

    suspend fun getAddressById(id: Int): AddressEntity? {
        return repository.getAddressById(id)
    }

    suspend fun getDefaultAddress(userId: String): AddressEntity? {
        return repository.getDefaultAddress(userId)
    }

    fun setDefaultAddress(address: AddressEntity) {
        viewModelScope.launch {
            repository.setDefaultAddress(address)
        }
    }

//    suspend fun hasAddress(userId: String): Boolean {
//        return repository.getAddressesByUserId(userId).isNotEmpty()
//    }
}