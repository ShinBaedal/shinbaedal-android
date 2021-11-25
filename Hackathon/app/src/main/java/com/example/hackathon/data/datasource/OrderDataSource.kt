package com.example.hackathon.data.datasource

import com.example.hackathon.data.api.OrderApi
import com.example.hackathon.domain.entity.Order
import com.example.hackathon.domain.response.BaseResponse
import com.example.hackathon.domain.response.DataResponse
import kotlinx.coroutines.flow.flow
import retrofit2.http.GET
import retrofit2.http.PATCH
import retrofit2.http.Path
import javax.inject.Inject

class OrderDataSource @Inject constructor(val orderApi: OrderApi) {
    suspend fun getOrders() =
        flow { emit(orderApi.getOrders()) }

    suspend fun doOrder(orderId: Long) = orderApi.patchOrder(orderId)

}