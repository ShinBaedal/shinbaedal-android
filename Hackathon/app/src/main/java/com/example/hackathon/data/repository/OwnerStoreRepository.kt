package com.example.hackathon.data.repository

import com.example.hackathon.domain.request.owner.PatchStoreRequest
import com.example.hackathon.domain.request.owner.PostStoreRequest
import com.example.hackathon.domain.response.BaseResponse
import kotlinx.coroutines.flow.Flow

interface OwnerStoreRepository {
    suspend fun patchStore(
        token: String,
        storeId: Int,
        request: PatchStoreRequest
    ): BaseResponse

    suspend fun postStore(request: PostStoreRequest): Flow<BaseResponse>
}