package com.example.basics.event

import com.example.basics.db.AddressEntity

sealed class AddressEvent {
    data class Edit(val address: AddressEntity) : AddressEvent()
    data class Delete(val address: AddressEntity) : AddressEvent()
    data class SetDefault(val address: AddressEntity) : AddressEvent()
}