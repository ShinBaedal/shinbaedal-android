package com.example.hackathon.data.repository.impl

import com.example.hackathon.data.datasource.OwnerStoreDataStore
import com.example.hackathon.domain.request.owner.PatchStoreRequest
import com.example.hackathon.domain.request.owner.PostStoreRequest
import javax.inject.Inject

class OwnerStoreRepositoryImpl @Inject constructor(private val dataSource:OwnerStoreDataStore) {

    suspend fun  patchStore(token:String, storeId:Int, request:PatchStoreRequest)=
        dataSource.patchStore(token,storeId,request)

    suspend fun postStore(token:String,request:PostStoreRequest)=
        dataSource.postStore(token,request)
}