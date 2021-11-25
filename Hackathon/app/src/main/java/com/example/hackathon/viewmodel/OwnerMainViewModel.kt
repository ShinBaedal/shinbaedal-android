package com.example.hackathon.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hackathon.data.repository.StoreRepository
import com.example.hackathon.domain.entity.Store
import com.example.hackathon.domain.response.DataState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OwnerMainViewModel @Inject constructor(private val storeRepository: StoreRepository) :
    BaseViewModel() {
    val storesState = MutableLiveData<DataState<List<Store>>>()
    fun getStores() {
        job = viewModelScope.launch {
            storesState.postValue(DataState.Loading)
            storeRepository.getStores("1", "").collect {
                storesState.postValue(DataState.Success(it.data))
            }
        }
    }


}