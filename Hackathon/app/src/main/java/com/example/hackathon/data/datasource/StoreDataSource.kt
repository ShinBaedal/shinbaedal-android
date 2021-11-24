package com.example.hackathon.data.datasource

import com.example.hackathon.data.api.StoreApi
import com.example.hackathon.domain.response.DataResponse
import com.example.hackathon.domain.response.Store
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class StoreDataSource @Inject constructor(private val storeApi: StoreApi) {

    suspend fun getStore(id: Long) = flow<DataResponse<Store>> { emit(storeApi.getStore(id)) }

    suspend fun getStores(address: String) = flow<DataResponse<List<Store>>> { emit(storeApi.getStores(address)) }

}