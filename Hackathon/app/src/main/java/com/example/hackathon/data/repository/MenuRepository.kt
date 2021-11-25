package com.example.hackathon.data.repository

import com.example.hackathon.domain.entity.Menu
import com.example.hackathon.domain.response.DataResponse
import kotlinx.coroutines.flow.Flow

interface MenuRepository  {
    suspend fun getMenus(storeId:Long): Flow<DataResponse<List<Menu>>>

}