package com.example.basics.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.basics.model.FeatureBrand
import com.example.basics.repository.RailRepository
import kotlinx.coroutines.launch

class RailViewModel: ViewModel() {

    private val repo= RailRepository()
    private val _rails= MutableLiveData<List<FeatureBrand>>()
    val rail: LiveData<List<FeatureBrand>> = _rails
    init{
        fetchRails()
    }
    fun fetchRails(){
        viewModelScope.launch {
            try {
                val result = repo.getRails()
                _rails.value=result
            } catch (e: Exception){
                e.printStackTrace()
            }
        }
    }

}