package com.example.hackathon.data.api

import com.example.hackathon.domain.response.DataResponse
import com.example.hackathon.domain.entity.Store
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Path

interface StoreApi {
    @GET("store/{store_id}")
    fun getStore(@Path("store_id") id: Long): DataResponse<Store>

    @GET("stores")
    fun getStores(@Body address: String): DataResponse<List<Store>>


}