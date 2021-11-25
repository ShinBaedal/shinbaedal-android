package com.example.hackathon.data.repository

import com.example.hackathon.domain.entity.Order
import com.example.hackathon.domain.response.DataResponse
import kotlinx.coroutines.flow.Flow


interface OrderRepository {
    suspend fun getOrders() : Flow<DataResponse<List<Order>>>
}