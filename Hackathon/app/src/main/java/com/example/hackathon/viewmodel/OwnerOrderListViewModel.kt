package com.example.hackathon.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hackathon.data.repository.OrderRepository
import com.example.hackathon.domain.entity.Order
import com.example.hackathon.domain.request.PostOrderRequest
import com.example.hackathon.domain.response.DataState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OwnerOrderListViewModel @Inject constructor(private val orderRepository: OrderRepository) :
    BaseViewModel() {
    val orderState = MutableLiveData<DataState<List<Order>>>()

    fun getOrders() {
        orderState.postValue(DataState.Loading)
        job = viewModelScope.launch {
            orderRepository.getOrders().collect {
                orderState.postValue(DataState.Success(it.data))
            }
        }
    }

    fun doOrder(orderId: Long) {
        job = viewModelScope.launch {
            orderRepository.doOrder(orderId)
        }
    }


}