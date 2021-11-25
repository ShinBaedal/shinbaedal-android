package com.example.hackathon.data.api

import android.view.Menu
import com.example.hackathon.domain.response.DataResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface MenuApi {
    @GET("menus/{store_id}")
    suspend fun getMenus(@Path("store_id") storeId: Long): DataResponse<Menu>
}