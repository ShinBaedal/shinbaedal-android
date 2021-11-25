package com.example.hackathon.data.api

import com.example.hackathon.domain.entity.Order
import com.example.hackathon.domain.request.PostOrderRequest
import com.example.hackathon.domain.response.BaseResponse
import com.example.hackathon.domain.response.DataResponse
import retrofit2.http.*

interface OrderApi {
    @POST("order")
    suspend fun postOrder(@Body body: PostOrderRequest) : BaseResponse

    @GET("order/my")
    suspend fun getOrders(@Path("order_id")orderId:Long):DataResponse<List<Order>>

    @PATCH("order/{order_id}")
    suspend fun patchOrder(@Path("order_id")orderId:Long):BaseResponse


}