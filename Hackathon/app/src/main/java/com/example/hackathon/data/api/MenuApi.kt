package com.example.hackathon.data.api

import com.example.hackathon.domain.entity.Menu
import com.example.hackathon.domain.response.DataResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface MenuApi {
    @GET("menu/list/{store_id}")
    suspend fun getMenus(@Path("store_id") storeId: Long): DataResponse<List<Menu>>
}