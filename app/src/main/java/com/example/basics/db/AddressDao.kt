package com.example.basics.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

@Dao
interface AddressDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAddress(address: AddressEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAddresses(addresses: List<AddressEntity>)

    @Update
    suspend fun updateAddress(address: AddressEntity)

    @Delete
    suspend fun deleteAddress(address: AddressEntity)

    @Query("SELECT * FROM address")
    suspend fun getAllAddresses(): List<AddressEntity>

    @Query("SELECT * FROM address WHERE userId = :userId")
    suspend fun getAddressesByUserId(userId: String): List<AddressEntity>

    @Query("SELECT * FROM address WHERE id = :id")
    suspend fun getAddressById(id: Int): AddressEntity?

    @Query("DELETE FROM address WHERE id = :id")
    suspend fun deleteAddressById(id: Int)

    @Query("DELETE FROM address")
    suspend fun deleteAllAddresses()

    @Query("SELECT * FROM address WHERE userId = :userId AND defaultAddress = 1 LIMIT 1")
    suspend fun getDefaultAddress(userId: String): AddressEntity?

    @Query("UPDATE address SET defaultAddress = 0 WHERE userId = :userId")
    suspend fun clearDefaultAddress(userId: String)


}