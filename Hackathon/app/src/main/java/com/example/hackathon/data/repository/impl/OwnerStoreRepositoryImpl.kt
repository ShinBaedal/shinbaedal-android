package com.example.hackathon.data.repository.impl

import com.example.hackathon.data.datasource.OwnerStoreDataSource
import com.example.hackathon.data.repository.OwnerStoreRepository
import com.example.hackathon.domain.request.owner.PatchStoreRequest
import com.example.hackathon.domain.request.owner.PostStoreRequest
import com.example.hackathon.domain.response.BaseResponse
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class OwnerStoreRepositoryImpl @Inject constructor(private val dataSource:OwnerStoreDataSource):
    OwnerStoreRepository {
    override suspend fun patchStore(
        storeId: Int,
        request: PatchStoreRequest
    ): BaseResponse {
        return dataSource.patchStore(storeId,request)
    }


    override suspend fun postStore(request: PostStoreRequest): Flow<BaseResponse> {
        return dataSource.postStore(request)

    }

}