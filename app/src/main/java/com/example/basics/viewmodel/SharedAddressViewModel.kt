package com.example.basics.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SharedAddressViewModel: ViewModel() {

    private val _saveClicked = MutableLiveData<Boolean>()
    val saveClicked: LiveData<Boolean> = _saveClicked

    fun onSaveClicked() {
        _saveClicked.value = true
    }

    fun resetSaveState() {
        _saveClicked.value = false
    }

    private val _addressSaved = MutableLiveData<Unit>()
    val addressSaved: LiveData<Unit> = _addressSaved
    fun onAddressSaved() {
        _addressSaved.value = Unit
    }
}