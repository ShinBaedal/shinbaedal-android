package com.example.hackathon.data.repository.impl

import com.example.hackathon.data.api.OrderApi
import com.example.hackathon.data.datasource.OrderDataSource
import com.example.hackathon.data.repository.OrderRepository
import com.example.hackathon.domain.entity.Order
import com.example.hackathon.domain.response.DataResponse
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class OrderRepositoryImpl @Inject constructor(private val orderDataSource: OrderDataSource) :
    OrderRepository {
    override suspend fun getOrders(): Flow<DataResponse<List<Order>>> = orderDataSource.getOrders()

}