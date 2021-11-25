package com.example.hackathon.data.repository.impl

import com.example.hackathon.data.datasource.StoreDataSource
import com.example.hackathon.data.repository.StoreRepository
import javax.inject.Inject

class StoreRepositoryImpl @Inject constructor(private val storeDataSource: StoreDataSource) :
    StoreRepository {
    override suspend fun getStore(id: Long) = storeDataSource.getStore(id)
    override suspend fun getStores(category: String, address: String) =
        storeDataSource.getStores(category, address)
}