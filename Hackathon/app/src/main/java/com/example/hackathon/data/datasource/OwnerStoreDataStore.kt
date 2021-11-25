package com.example.hackathon.data.datasource

import com.example.hackathon.data.api.OwnerStoreApi
import com.example.hackathon.domain.request.owner.PatchStoreRequest
import com.example.hackathon.domain.request.owner.PostStoreRequest
import javax.inject.Inject

class OwnerStoreDataStore @Inject constructor(private val api : OwnerStoreApi) {

    suspend fun patchStore(token:String,storeId:Int,request:PatchStoreRequest)=
        api.patchStore(token,storeId,request)

    suspend fun postStore(token:String,request:PostStoreRequest)=
        api.postStore(token,request)
}