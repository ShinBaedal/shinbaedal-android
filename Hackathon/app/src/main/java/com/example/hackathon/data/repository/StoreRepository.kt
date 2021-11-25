package com.example.hackathon.data.repository

import com.example.hackathon.domain.response.DataResponse
import com.example.hackathon.domain.entity.Store
import kotlinx.coroutines.flow.Flow

interface StoreRepository {
    suspend fun getStore(id: Long): Flow<DataResponse<Store>>
    suspend fun getStores(category: String, address: String): Flow<DataResponse<List<Store>>>
}