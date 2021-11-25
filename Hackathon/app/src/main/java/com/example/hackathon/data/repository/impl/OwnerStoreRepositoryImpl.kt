package com.example.hackathon.data.repository.impl

import com.example.hackathon.data.datasource.OwnerStoreDataSource
import com.example.hackathon.data.repository.OwnerStoreRepository
import com.example.hackathon.domain.request.owner.PatchStoreRequest
import com.example.hackathon.domain.request.owner.PostStoreRequest
import com.example.hackathon.domain.response.BaseResponse
import javax.inject.Inject

class OwnerStoreRepositoryImpl @Inject constructor(private val dataSourceDataSource: OwnerStoreDataSource) :
    OwnerStoreRepository {


    override suspend fun patchStore(
        token: String,
        storeId: Int,
        request: PatchStoreRequest
    ): BaseResponse = dataSourceDataSource.patchStore(storeId, request)


    override suspend fun postStore(request: PostStoreRequest) =
        dataSourceDataSource.postStore(request)
}