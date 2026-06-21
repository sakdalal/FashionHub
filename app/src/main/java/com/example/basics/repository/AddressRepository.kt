package com.example.basics.repository

import com.example.basics.db.AddressDao
import com.example.basics.db.AddressEntity
import kotlinx.coroutines.flow.Flow

class AddressRepository(
    private val addressDao: AddressDao
) {

    suspend fun insertAddress(address: AddressEntity) {
        if(address.defaultAddress) {
            addressDao.clearDefaultAddress(address.userId)
        }
        addressDao.insertAddress(address)
    }

    suspend fun insertAddresses(addresses: List<AddressEntity>) {
        addressDao.insertAddresses(addresses)
    }

    suspend fun updateAddress(address: AddressEntity) {
        if(address.defaultAddress) {
            addressDao.clearDefaultAddress(address.userId)
        }

        addressDao.updateAddress(address)
    }

    suspend fun deleteAddress(address: AddressEntity) {
        addressDao.deleteAddress(address)
    }

    suspend fun getAllAddresses(): List<AddressEntity> {
        return addressDao.getAllAddresses()
    }

    fun getAddressesByUserId(userId: String): Flow<List<AddressEntity>> {
        return addressDao.getAddressesByUserId(userId)
    }

    suspend fun getAddressById(id: Int): AddressEntity? {
        return addressDao.getAddressById(id)
    }

    suspend fun deleteAddressById(id: Int) {
        addressDao.deleteAddressById(id)
    }

    suspend fun deleteAllAddresses() {
        addressDao.deleteAllAddresses()
    }

    suspend fun getDefaultAddress(userId: String): AddressEntity? {
        return addressDao.getDefaultAddress(userId)
    }

    suspend fun setDefaultAddress(address: AddressEntity) {
        addressDao.clearDefaultAddress(address.userId)

        addressDao.updateAddress(
            address.copy(defaultAddress = true)
        )
    }
}
